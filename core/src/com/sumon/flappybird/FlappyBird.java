package com.sumon.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sumon.flappybird.states.GameSateManager;
import com.sumon.flappybird.states.MenuState;
import com.sumon.flappybird.utils.StaticAccess;

public class FlappyBird extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    private GameSateManager gsm;

    private SpriteBatch batch;
    Texture img;
    private Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameSateManager();
        music=Gdx.audio.newMusic(Gdx.files.internal(StaticAccess.FLAPPY_BIRD_MUSIC));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        music.dispose();
    }
}
