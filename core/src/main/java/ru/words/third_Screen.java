package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class third_Screen implements  Screen{
    Main main;
    private Texture d1,bk,ab;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public int volumes;

    public Music oiia;
    public third_Screen(Main main){
        this.main = main;
        oiia = main.cl;
        volumes = main.volume;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        d1 = new Texture("bg.jpg");
        bk = new Texture("back.png");
        ab = new Texture("about.png");


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y> 1500 && touch.y < 1600 && touch.x > 0 && touch.x < 100){
                oiia.play();
                oiia.setVolume((main.volume/9f)/ 100f);
                main.setScreen(main.justScreen);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d1,0,0,900,1600);

        batch.draw(bk,0,1500,100,100);
        batch.draw(ab,0,0,900,1400);





        batch.end();

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
        d1.dispose();
        bk.dispose();
        oiia.dispose();
        batch.dispose();

    }
}
