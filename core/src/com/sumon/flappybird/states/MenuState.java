package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sumon.flappybird.FlappyBird;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by Sumon on 5/4/2017.
 */

public class MenuState extends State {
    private Texture bg;
    private Texture playBtn;
    BitmapFont font;

    public MenuState(GameSateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        /// need to open sb first
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0);

        spriteBatch.draw(playBtn, (cam.position.x) - (playBtn.getWidth() / 2), cam.position.y);
        font.setColor(Color.WHITE);


        // We specify the name of the Preferences File
        Preferences prefs = Gdx.app.getPreferences("PreferenceName");// We store the value 10 with the key of "highScore"
        prefs.putInteger("highScore", 10);
        prefs.flush(); // This saves the preferences file.

        font.draw(spriteBatch, "SCORE:" + java.lang.String.valueOf(prefs.getInteger("highScore")), (cam.position.x) - (playBtn.getWidth() / 2), cam.position.y + cam.position.y / 2);
        spriteBatch.end();
    }

    @Override
    protected void dispose() {
        bg.dispose();
        playBtn.dispose();

    }

}
