package com.mygdx.mole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    ShapeRenderer shape;
    Texture backgroundTexture;
    Sprite backgroundSprite;

    static float buttonWidth = 100f;
    static float buttonHeight = 30f;
    static float buttonX = Gdx.graphics.getWidth() / 2f - buttonWidth / 2f;
    static float buttonY = Gdx.graphics.getHeight() / 2f;
    private Stage stage;
    private MainGame game;

    public MenuScreen(final MainGame game) {
        this.game = game;

        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false);

        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        backgroundTexture = new Texture("data/ground.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(width, height);

        stage = new Stage(new ScreenViewport());

        Label title = new Label("Whack-a-Mole", MainGame.gameSkin, "title-white");
        title.setWidth(width);
        title.setY(height * 2/3 - 30);
        title.setAlignment(Align.center);
        stage.addActor(title);

        Button startButton = new TextButton("Start Game", MainGame.gameSkin, "maroon");
        startButton.setSize(buttonWidth, buttonHeight);
        startButton.setPosition(buttonX, buttonY);
        startButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MoleGame(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button exitButton = new TextButton("Exit", MainGame.gameSkin, "maroon");
        exitButton.setSize(buttonWidth, buttonHeight);
        exitButton.setPosition(buttonX, buttonY - 50);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });


        stage.addActor(startButton);
        stage.addActor(exitButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // ScreenUtils.clear(Color.BLACK);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();

        stage.act();
        stage.draw();

        // shape.begin(ShapeRenderer.ShapeType.Filled);
        // shape.rect(buttonX, buttonY, buttonWidth, buttonHeight);
        // shape.end();
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
        shape.dispose();
        stage.dispose();
    }
}
