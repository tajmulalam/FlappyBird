package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sumon.flappybird.FlappyBird;
import com.sumon.flappybird.utils.MyPreference;

/**
 * Created by Sumon on 5/4/2017.
 */

public class GameOverState extends State {
    private Texture bg;
    private Texture gameOverTexture;
    BitmapFont font;

    public GameOverState(GameSateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        gameOverTexture = new Texture("gameover.png");
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.WHITE);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));

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
        spriteBatch.draw(gameOverTexture, (cam.position.x) - (gameOverTexture.getWidth() / 2), cam.position.y + 50);
        font.draw(spriteBatch, "SCORE: " + java.lang.String.valueOf(MyPreference.getScore()), (cam.position.x) - (gameOverTexture.getWidth() / 3) + 10, cam.position.y + 30);
        spriteBatch.end();
    }

    @Override
    protected void dispose() {
        bg.dispose();
        gameOverTexture.dispose();

    }

}
