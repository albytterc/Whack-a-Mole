package com.mygdx.mole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.managers.GameManager;
import com.mygdx.managers.InputManager;
import com.mygdx.managers.TextManager;

public class MoleGame implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    private Stage stage;

    public MoleGame(final MainGame game) {
        stage = new Stage(new ScreenViewport());



		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false);
        GameManager.initialize(width, height);

        Button backButton = new TextButton("Back", MainGame.gameSkin, "maroon");
        backButton.setPosition(10, height - 30f);
        // System.out.println(TextManager.glyphLayout.height);
        backButton.setSize(50, 25);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(backButton);

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        InputManager.handleInput(camera);
		batch.setProjectionMatrix(camera.combined);
        batch.begin();
		GameManager.renderGame(batch);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
		GameManager.dispose();
        stage.dispose();
    }
}
