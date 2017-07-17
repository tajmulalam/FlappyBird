package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sumon.flappybird.FlappyBird;
import com.sumon.flappybird.utils.MyPreference;
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
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.WHITE);

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
        font.draw(spriteBatch, "SCORE:" + java.lang.String.valueOf(MyPreference.getHighScore()), (cam.position.x) - (playBtn.getWidth() / 2), cam.position.y + cam.position.y / 2);
        spriteBatch.end();

    }

    @Override
    protected void dispose() {
        bg.dispose();
        playBtn.dispose();

    }

}
