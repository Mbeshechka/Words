package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sun.jdi.FloatValue;

import java.util.Collections;

public class just_Screen implements  Screen{
    Main main;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    private Texture d1,led,inf,sett,ex,pl;
    public Boolean ran;
    public int sc,volume;
    public Music oiia;
    first_screen first_screen;
    public just_Screen(Main main){
        this.main = main;
        oiia = main.cl;
        volume = main.volume / 9;
        batch = main.batch;
        sc = main.whichSc;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        ran = main.random_word;


        led = new Texture("leader.png");
        inf = new Texture("inf.png");
        sett = new Texture("settings.png");
        ex = new Texture("exit.png");
        d1 = new Texture("bg.jpg");
        pl = new Texture("play.png");



    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y > 0 && touch.y < 225 && touch.x > 450 && touch.x < 675){
                oiia.play();
                oiia.setVolume(((float)(main.volume/9))/ 100);


                main.setScreen(main.firstScreen);
            }
            else if(touch.y < 1000+233 && touch.y > 1000 && touch.x > 100 && touch.x < 800){
                if (main.random_word){
                    oiia.play();
                    oiia.setVolume(((float)(main.volume/9))/ 100);
                    Collections.shuffle(main.myList);
                    //System.out.println(myList);
                    main.word = main.myList.get(0);
                    main.setScreen(main.fourthScreen);
                }
                else{
                    oiia.play();
                    oiia.setVolume(((float)(main.volume/9))/ 100);
                    main.setScreen(main.secondScreen);
                }
            }
            else if(touch.y > 0 && touch.y < 225 && touch.x > 675 && touch.x < 900){
                oiia.play();
                oiia.setVolume(((float)(main.volume/9))/ 100);
                main.setScreen(main.thirdScreen);
            }
            else if(touch.y > 0 && touch.y < 225 && touch.x > 0 && touch.x < 225){
                Gdx.app.exit();
            }
            //drill down to leaderboard
            else if(touch.y > 0 && touch.y < 225 && touch.x > 225 && touch.x < 450){
                oiia.play();
                oiia.setVolume(((float)(main.volume/9))/ 100);
                main.led.LoadDB();
                main.setScreen(main.led);

            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d1,0,0,900,1600);
        batch.draw(ex,0,0,225,225);
        batch.draw(led,225,0,225,225);
        batch.draw(sett,450,0,225,225);
        batch.draw(inf,675,0,225,225);
        batch.draw(pl,100,1000,700,233);








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
        ex.dispose();
        led.dispose();
        inf.dispose();
        sett.dispose();
        pl.dispose();
        oiia.dispose();
        batch.dispose();

    }
}
