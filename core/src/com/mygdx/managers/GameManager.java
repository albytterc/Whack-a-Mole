package com.mygdx.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gameobjects.Mole;

public class GameManager {
    static Array<Mole> moles;
    static Texture moleTexture;

    static Texture stunTexture;

    static Texture backgroundTexture;
    public static Sprite backgroundSprite;

    static Texture holeTexture;
    static Array<Sprite> holeSprites;

    private static final float HOLE_RESIZE_FACTOR = 1100f;

    public static int score;
    public static Sound hitSound;

    public static void initialize(float width, float height) {
        score = 0;
        moles = new Array<>();
        moleTexture = new Texture("data/mole.png");

        for (int i = 0; i < 9; i++) {
            moles.add(new Mole());
        }

        backgroundTexture = new Texture("data/ground.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(width, height);

        holeTexture = new Texture("data/hole.png");
        holeSprites = new Array<>();

        stunTexture = new Texture("data/stun.png");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Sprite sprite = new Sprite(holeTexture);
                sprite.setSize(sprite.getWidth() * (width / HOLE_RESIZE_FACTOR), sprite.getHeight() * (width / HOLE_RESIZE_FACTOR));
                sprite.setPosition(width * (j + 1) / 4f - sprite.getWidth() / 2f, height * (i + 1) / 4.4f - sprite.getHeight());
                holeSprites.add(sprite);
            }
        }

        for (int i = 0; i < 9; i++) {
            Mole mole = moles.get(i);
            Sprite sprite = holeSprites.get(i);

            mole.moleSprite = new Sprite(moleTexture);
            mole.stunSprite = new Sprite(stunTexture);

            float scaleFactor = width / 4000f;
            mole.scaleFactor = scaleFactor;
            mole.width = mole.moleSprite.getWidth() * scaleFactor;
            mole.height = mole.moleSprite.getHeight() * scaleFactor;
            mole.currentHeight = mole.height * (float) Math.random();
            mole.moleSprite.setSize(mole.width, mole.height);

			//set mole's position
			mole.position.x=(((2*sprite.getX() + sprite.getWidth())/2)  - (mole.moleSprite.getWidth()/2));
			mole.position.y=(sprite.getY() + sprite.getHeight()/5f);

            mole.moleSprite.setPosition(mole.position.x, mole.position.y);
            mole.stunSprite.setSize(mole.width/2f, mole.height/2f);
            mole.randomizeWaitTime();
        }

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/whack.wav"));
        TextManager.initialize(width, height);
    }

    public static void renderGame(SpriteBatch batch) {
        backgroundSprite.draw(batch);

        for (Sprite sprite : holeSprites) {
            sprite.draw(batch);
        }

        for (Mole mole : moles) {
            mole.update();
            mole.render(batch);
        }
        TextManager.renderText(batch);
    }

    public static void dispose() {
        backgroundTexture.dispose();
        holeTexture.dispose();
        moleTexture.dispose();
        stunTexture.dispose();
        hitSound.dispose();
    }
}
