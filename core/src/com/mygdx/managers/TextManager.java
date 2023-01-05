package com.mygdx.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextManager {
    static BitmapFont font;
    static float width, height;
    public static GlyphLayout glyphLayout;


    public static void initialize(float width, float height) {
        font = new BitmapFont(Gdx.files.absolute("C:\\Users\\albyt\\IdeaProjects\\LibGDXDemo\\assets\\arial32.fnt"));
        TextManager.width = width;
        TextManager.height = height;
        font.setColor(Color.WHITE);
        font.getData().setScale(0.75f);
        glyphLayout = new GlyphLayout();
    }

    public static void renderText(SpriteBatch batch) {
        glyphLayout.setText(font, "SCORE: " + GameManager.score);
        font.draw(batch, glyphLayout, width - glyphLayout.width - 20, height - 10);
    }
}
