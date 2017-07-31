package com.sumon.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sumon.flappybird.FlappyBird;
import com.sumon.flappybird.utils.StaticAccess;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = FlappyBird.WIDTH;
        config.height = FlappyBird.HEIGHT;
        config.title = StaticAccess.GAME_TITLE;
        new LwjglApplication(new FlappyBird(), config);
    }
}
