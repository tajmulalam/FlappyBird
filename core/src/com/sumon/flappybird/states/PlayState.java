package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sumon.flappybird.FlappyBird;
import com.sumon.flappybird.sprites.Bird;
import com.sumon.flappybird.sprites.Tube;
import com.sumon.flappybird.utils.MyPreference;

import sun.rmi.runtime.Log;

/**
 * Created by Sumon on 5/5/2017.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Array<Tube> tubes;
    private Vector2 groundPos1, groundPos2;
    BitmapFont font;

    public PlayState(GameSateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.WHITE);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    int score = 0;
    int life = 3;
    int success = 0;

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                score=score+5;
            }

            if (tube.collides(bird.getBounds())) {
                gameOverDetect();
            }
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            gameOverDetect();
        }

        cam.update();
    }

    void gameOverDetect() {
        if (MyPreference.getHighScore() < score) {
            MyPreference.saveHighScore(score);
        }
        MyPreference.saveScore(score);
        score = 0;
        gsm.set(new GameOverState(gsm));
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);

            //System.out.print(" Top:  X "+tube.getPosTopTube().x+tube.getTopTube().getWidth()+" Y "+tube.getPosTopTube().y);
            //System.out.print(" bird:  X "+bird.getPosition().x+bird.getBird().getRegionWidth()+" Y "+bird.getPosition().y);

        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
        font.draw(spriteBatch, "Score: " + String.valueOf(score), cam.position.x - cam.viewportWidth / 2 + 5, cam.position.y + cam.viewportHeight / 2 - 20);
        spriteBatch.end();
    }

    @Override
    protected void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        //System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
