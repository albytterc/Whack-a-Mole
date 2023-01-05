package com.mygdx.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.managers.GameManager;

public class Mole {
    public Sprite moleSprite;
    public Vector2 position = new Vector2();
    public float height, width;

    public float scaleFactor = 4000f;

    public enum State {
        GO_UP, GO_DOWN, UNDERGROUND, STUNNED
    }

    public State state = State.GO_UP;
    public float currentHeight = 0f;
    public float speed = 2f;

    public float timeUnderground = 0f;
    public float maxTimeUnderGround = 0.8f;

    public float maxStunTime = 0.1f;
    public float stunTime = 0f;

    public Sprite stunSprite;

    public void update() {
        switch (state) {
            case STUNNED:
                if (stunTime >= maxStunTime) {
                    state = State.UNDERGROUND;
                    stunTime = 0f;
                    currentHeight = 0f;
                    randomizeWaitTime();
                } else {
                    stunTime += Gdx.graphics.getDeltaTime();
                }
                break;
            case UNDERGROUND:
                if (timeUnderground >= maxTimeUnderGround) {
                    state = State.GO_UP;
                    timeUnderground = 0f;
                } else {
                    timeUnderground += Gdx.graphics.getDeltaTime();
                }
                break;
            case GO_UP:
                currentHeight += speed;
                if (currentHeight >= height) {
                    currentHeight = height;
                    state = State.GO_DOWN;
                }
                break;
            case GO_DOWN:
                currentHeight -= speed;
                if (currentHeight <= 0) {
                    currentHeight = 0f;
                    state = State.UNDERGROUND;
                }
                break;
        }
        moleSprite.setRegion(0, 0, (int) (width / scaleFactor), (int) (currentHeight / scaleFactor));
        moleSprite.setSize(moleSprite.getWidth(), currentHeight);
    }

    public void render(SpriteBatch batch) {
        moleSprite.draw(batch);
        if (state == State.STUNNED) {
            stunSprite.draw(batch);
        }
    }

    public void randomizeWaitTime() {
        maxTimeUnderGround = (float) Math.random() * 2f;
    }

    public boolean handleTouch(float touchX, float touchY) {
        if ((touchX >= position.x) && touchX <= (position.x + width) && (touchY >= position.y) && touchY <= (position.y + currentHeight)) {
            stunSprite.setPosition(position.x + width/2 - (stunSprite.getWidth() / 2), position.y + currentHeight - (stunSprite.getHeight() / 2));
            state = State.STUNNED;
            GameManager.hitSound.play();
            return true;
        }
        return false;
    }
}
