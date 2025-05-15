package ru.words;



import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

public class button {
    public static final float SCREEN_WIDTH = 900;
    public static final float SCREEN_HEIGHT = 1600;
    BitmapFont font;
    String text;
    float x, y;
    float width, height;

    public button(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }

    public button(BitmapFont font, String text, float y) {
        this.font = font;
        this.text = text;
        this.y = y;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
        this.x = SCREEN_WIDTH/2-width/2;
    }

    public void setText(String text){
        this.text = text;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
    }

    public void setFont(BitmapFont font){
        this.font = font;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }

    public boolean hit(float tx, float ty){

        return x<ty && ty<x+width && y>tx && tx>y-height;
    }

    public boolean hit(Vector3 t){
        return x<t.x && t.x<x+width && y>t.y && t.y>y-height;
    }
}
