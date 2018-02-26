/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

/**
 *
 * @author asus
 */
public class datapik {
  // Factory to create Cell of DatePicker
public static Callback<DatePicker, DateCell> getDayCellFactory() {
    LocalDate d= LocalDate.now();
final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
@Override
public DateCell call(final DatePicker datePicker) {
return new DateCell() {
@Override
public void updateItem(LocalDate item, boolean empty) {
super.updateItem(item, empty);
                 // Disable Monday, Tueday, Wednesday.
if (item.isBefore(d)) {
setDisable(true);
setStyle("-fx-background-color: #ffc0cb;");
}
}
};
}
};
return dayCellFactory;
}

}
