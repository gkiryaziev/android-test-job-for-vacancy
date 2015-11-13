package android.ua.testapp;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Admin on 09.11.2015.
 */
public class ColorChanger {

    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
    }

}
