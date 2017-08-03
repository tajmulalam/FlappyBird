package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sumon.flappybird.FlappyBird;
import com.sumon.flappybird.utils.MyPreference;
import com.sumon.flappybird.utils.StaticAccess;


/**
 * Created by Sumon on 5/4/2017.
 */

public class GameOverState extends State {
    private Texture bg;
    private Texture gameOverTexture;
    private Texture playAgain;
    BitmapFont font;
    int score = -1;
    Rectangle plyBtnRect;

    public GameOverState(GameSateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture(StaticAccess.GAME_OVER_STATE_TEXTURE_BG);
        gameOverTexture = new Texture(StaticAccess.GAME_OVER_STATE_TEXTURE);
        playAgain = new Texture(StaticAccess.GAME_OVER_PLAY_AGAIN);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.WHITE);
        score = MyPreference.getScore();
        plyBtnRect = new Rectangle(cam.position.x - ((playAgain.getWidth() * 25) / 100), cam.position.y - ((FlappyBird.HEIGHT * 10) / 100), 70, 70);

    }

    @Override
    public void handleInput() {
        Vector3 touchPoint = new Vector3();
        if (Gdx.input.justTouched()) {
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (plyBtnRect.contains(touchPoint.x, touchPoint.y)) {
                // will be here when balloon will be touched
                gsm.set(new MenuState(gsm));


            }
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
        spriteBatch.draw(playAgain, cam.position.x - ((playAgain.getWidth() * 22) / 100), cam.position.y - ((FlappyBird.HEIGHT * 10) / 100), 70, 70);
        font.draw(spriteBatch, "SCORE: " + java.lang.String.valueOf(score), (cam.position.x) - (gameOverTexture.getWidth() / 4) + 10, cam.position.y + 30);
        spriteBatch.end();
    }

    @Override
    protected void dispose() {
        bg.dispose();
        gameOverTexture.dispose();
        playAgain.dispose();
    }

}
