
package Presentation;

import java.io.FileInputStream;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author feriel
 */
public class MediaControl extends BorderPane {

    private MediaPlayer mp;
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    private HBox mediaBar;
    private final Image PlayButtonImage = new Image("/images/playbutton.png");
    private final Image PauseButtonImage = new Image("/images/pausebutton.png");
    ImageView imageViewPlay = new ImageView(PlayButtonImage);
    ImageView imageViewPause = new ImageView(PauseButtonImage);
    private Pane mvPane;
    private Stage newStage;
    private boolean fullScreen = false;

    MediaControl(FileInputStream input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void layoutChildren() {
        if (mediaView != null && getBottom() != null) {
            mediaView.setFitWidth(getWidth());
            mediaView.setFitHeight(getHeight() - getBottom().prefHeight(-1));
        }
        super.layoutChildren();
        if (mediaView != null && getCenter() != null) {
            mediaView.setTranslateX((((Pane) getCenter()).getWidth() - mediaView.prefWidth(-1)) / 2);
            mediaView.setTranslateY((((Pane) getCenter()).getHeight() - mediaView.prefHeight(-1)) / 2);
        }
    }

    @Override
    protected double computeMinWidth(double height) {
        return mediaBar.prefWidth(-1);
    }

    @Override
    protected double computeMinHeight(double width) {
        return 200;
    }

    @Override
    protected double computePrefWidth(double height) {
        return Math.max(mp.getMedia().getWidth(), mediaBar.prefWidth(height));
    }

    @Override
    protected double computePrefHeight(double width) {
        return mp.getMedia().getHeight() + mediaBar.prefHeight(width);
    }

    @Override
    protected double computeMaxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    protected double computeMaxHeight(double width) {
        return Double.MAX_VALUE;
    }

    public MediaControl(final MediaPlayer mp) {
        this.mp = mp;
        setStyle("-fx-background-color: #bfc2c7;"); // TODO: Use css file
        mediaView = new MediaView(mp);
        mvPane = new Pane();
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;"); // TODO: Use css file
        setCenter(mvPane);
        mediaBar = new HBox(5.0);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        mediaBar.setAlignment(Pos.CENTER_LEFT);
        BorderPane.setAlignment(mediaBar, Pos.CENTER);

        final Button playButton = ButtonBuilder.create()
                .minWidth(Control.USE_PREF_SIZE)
                .build();

        playButton.setGraphic(imageViewPlay);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                updateValues();
                MediaPlayer.Status status = mp.getStatus();
                if (status == MediaPlayer.Status.UNKNOWN
                        || status == MediaPlayer.Status.HALTED) {
                    // don't do anything in these states
                    return;
                }

                if (status == MediaPlayer.Status.PAUSED
                        || status == MediaPlayer.Status.READY
                        || status == MediaPlayer.Status.STOPPED) {
                    // rewind the movie if we're sitting at the end
                    if (atEndOfMedia) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                        playButton.setGraphic(imageViewPlay);
                        //playButton.setText(">");
                        updateValues();
                    }
                    mp.play();
                    playButton.setGraphic(imageViewPause);
                    //playButton.setText("||");
                } else {
                    mp.pause();
                }
            }
        });
        mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                updateValues();
            }
        });
        mp.setOnPlaying(new Runnable() {
            public void run() {

                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setGraphic(imageViewPause);
                    //playButton.setText("||");
                }
            }
        });
        mp.setOnPaused(new Runnable() {
            public void run() {

                playButton.setGraphic(imageViewPlay);
                //playButton.setText("||");
            }
        });
        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });

        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setGraphic(imageViewPlay);
                    //playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });
        mediaBar.getChildren().add(playButton);

        // Time label
        Label timeLabel = new Label("Time");
        timeLabel.setMinWidth(Control.USE_PREF_SIZE);
        mediaBar.getChildren().add(timeLabel);

        // Time slider
        timeSlider = SliderBuilder.create()
                .minWidth(30)
                .maxWidth(Double.MAX_VALUE)
                .build();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    if (duration != null) {
                        mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                    }
                    updateValues();

                }
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Play label
        playTime = LabelBuilder.create()
                //.prefWidth(130)
                .minWidth(Control.USE_PREF_SIZE)
                .build();

        mediaBar.getChildren().add(playTime);

        //Fullscreen button
        Button buttonFullScreen = ButtonBuilder.create()
                .text("Full Screen")
                .minWidth(Control.USE_PREF_SIZE)
                .build();

        buttonFullScreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!fullScreen) {
                    newStage = new Stage();
                    newStage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                            onFullScreen();
                        }
                    });
                    final BorderPane borderPane = new BorderPane() {
                        @Override
                        protected void layoutChildren() {
                            if (mediaView != null && getBottom() != null) {
                                mediaView.setFitWidth(getWidth());
                                mediaView.setFitHeight(getHeight() - getBottom().prefHeight(-1));
                            }
                            super.layoutChildren();
                            if (mediaView != null) {
                                mediaView.setTranslateX((((Pane) getCenter()).getWidth() - mediaView.prefWidth(-1)) / 2);
                                mediaView.setTranslateY((((Pane) getCenter()).getHeight() - mediaView.prefHeight(-1)) / 2);
                            }
                        }
                    ;
                    };
                   
                    setCenter(null);
                    setBottom(null);
                    borderPane.setCenter(mvPane);
                    borderPane.setBottom(mediaBar);

                    Scene newScene = new Scene(borderPane);
                    newStage.setScene(newScene);
                    //Workaround for disposing stage when exit fullscreen
                    newStage.setX(-100000);
                    newStage.setY(-100000);

                    newStage.setFullScreen(true);
                    fullScreen = true;
                    newStage.show();

                } else {
                    //toggle FullScreen
                    fullScreen = false;
                    newStage.setFullScreen(false);

                }
            }

        });
        mediaBar.getChildren().add(buttonFullScreen);

        // Volume label
        Label volumeLabel = new Label("Vol");
        volumeLabel.setMinWidth(Control.USE_PREF_SIZE);
        mediaBar.getChildren().add(volumeLabel);

        // Volume slider
        volumeSlider = SliderBuilder.create()
                .prefWidth(70)
                .minWidth(30)
                .maxWidth(Region.USE_PREF_SIZE)
                .build();
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
            }
        });
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
        mediaBar.getChildren().add(volumeSlider);

        setBottom(mediaBar);

    }

    protected void onFullScreen() {
        if (!newStage.isFullScreen()) {

            fullScreen = false;
            setCenter(mvPane);
            setBottom(mediaBar);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    newStage.close();
                }
            });

        }
    }

    protected void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null && duration != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
                    }
                }
            });
        }
    }

    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            }
        } else if (elapsedHours > 0) {
            return String.format("%d:%02d:%02d",
                    elapsedHours, elapsedMinutes, elapsedSeconds);
        } else {
            return String.format("%02d:%02d",
                    elapsedMinutes, elapsedSeconds);
        }
    }
}
