package com.sumon.flappybird.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Sumon on 7/17/2017.
 */

public class MyPreference {

    public static int getHighScore() {
        // We specify the name of the Preferences File
        Preferences prefs = Gdx.app.getPreferences("HighScorePref");// We store the value 10 with the key of "highScore"
        return prefs.getInteger("highScore");
    }
    public static void saveHighScore(int highScore) {
        // We specify the name of the Preferences File
        Preferences prefs = Gdx.app.getPreferences("HighScorePref");// We store the value 10 with the key of "highScore"
        prefs.putInteger("highScore", highScore);
        prefs.flush(); // This saves the preferences file.
    }

    public static void saveScore(int score) {
        // We specify the name of the Preferences File
        Preferences prefs = Gdx.app.getPreferences("ScorePref");// We store the value 10 with the key of "highScore"
        prefs.putInteger("Score", score);
        prefs.flush(); // This saves the preferences file.
    }

    public static int getScore() {
        // We specify the name of the Preferences File
        Preferences prefs = Gdx.app.getPreferences("ScorePref");// We store the value 10 with the key of "highScore"
        return prefs.getInteger("Score");
    }
}
