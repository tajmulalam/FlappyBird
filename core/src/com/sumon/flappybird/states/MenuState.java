package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sumon.flappybird.FlappyBird;
import com.sumon.flappybird.utils.MyPreference;
import com.sumon.flappybird.utils.StaticAccess;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by Sumon on 5/4/2017.
 */

public class MenuState extends State {
    private Texture bg;
    private Texture playBtn;
    private Texture logo;
    BitmapFont font;

    Rectangle plyBtnRect;

    public MenuState(GameSateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture(StaticAccess.MENU_STATE_MAIN_BG);
        playBtn = new Texture(StaticAccess.MENU_STATE_PLAY_BTN);
        logo = new Texture(StaticAccess.MENU_STATE_LOGO);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.WHITE);
        plyBtnRect = new Rectangle((cam.position.x) - (playBtn.getWidth() / 2), cam.position.y, playBtn.getWidth(), playBtn.getHeight());

    }

    @Override
    public void handleInput() {
        Vector3 touchPoint = new Vector3();
        if (Gdx.input.justTouched()) {
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (plyBtnRect.contains(touchPoint.x, touchPoint.y)) {
                // will be here when balloon will be touched
                gsm.set(new PlayState(gsm));

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
        spriteBatch.draw(playBtn, (cam.position.x) - (playBtn.getWidth() / 2), cam.position.y);
        font.draw(spriteBatch,
                StaticAccess.MENU_STATE_HIGH_SCORE + java.lang.String.valueOf(MyPreference.getHighScore()),
                (cam.position.x) - (playBtn.getWidth() / 2), cam.position.y + cam.position.y / 2);
        spriteBatch.draw(logo, (cam.position.x) - (logo.getWidth() / 2),
                (cam.position.y + cam.position.y / 2) + (cam.position.y * 10) / 100);
        spriteBatch.end();

    }

    @Override
    protected void dispose() {
        bg.dispose();
        playBtn.dispose();
        logo.dispose();

    }

}
