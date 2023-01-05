package com.mygdx.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gameobjects.Mole;

public class InputManager {
    static Vector3 temp = new Vector3();

    public static void handleInput(OrthographicCamera camera) {
        if (Gdx.input.justTouched()) {
            // Get input touch coordinates and set the temp vector with these values
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // get the touch co-ordinates with respect to the camera's viewport
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;


            for (int i = 0; i < GameManager.moles.size; i++) {
                Mole mole = GameManager.moles.get(i);

                if (mole.state != Mole.State.STUNNED && mole.handleTouch(touchX, touchY)) {
                    GameManager.score++;
                }
            }
        }
    }
}
