/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

//import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Fatma
 */
public class util {
      public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
      

}
