package com.mygdx.mole;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MainGame extends Game {
    public static Skin gameSkin;
    @Override
    public void create() {
        gameSkin = new Skin(Gdx.files.absolute("C:\\Users\\albyt\\Downloads\\gdx-skins-master\\gdx-skins-master\\orange\\skin\\uiskin.json"));
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
