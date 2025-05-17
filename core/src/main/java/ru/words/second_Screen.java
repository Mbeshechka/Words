package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class second_Screen implements  Screen{
    Main main;
    int x1,y1;
    private Texture d11,bk,joj;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public int volumes,lenh;
    private KeyBoard keyboard;

    BitmapFont font70;
    String text,alpthabet;

    public Music oiia;
    public second_Screen(Main main){
        this.main = main;
        volumes = main.volume;
        oiia = main.cl;
        text = "A";
        alpthabet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        d11 = new Texture("bg.jpg");
        bk = new Texture("back.png");

        font70 = new BitmapFont(Gdx.files.internal("stylo90gray.fnt"));
        if (main.language == 0){
          keyboard = new KeyBoard( 900, 300);}
        else{
            keyboard = new KeyBoard( 900, 300,1);
        }



    }
    @Override
    public void show() {
        if (main.language == 0){
            keyboard = new KeyBoard( 900, 300);}
        else{
            keyboard = new KeyBoard( 900, 300,1);
        }

    }
    public void print(String word, float x, float y, float width, float height,SpriteBatch batch) {
        Texture letter;
        String alp = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890ЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
        for (int i = 0; i < word.length(); i++) {
            if (alp.contains(String.valueOf(word.toUpperCase().charAt(i)))){
                letter = new Texture(word.toUpperCase().charAt(i) + ".png");
                batch.draw(letter, x + ( width /(float) word.length()) * i, y,  width / (float) word.length(), height);
            }
        }
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y> 1500 && touch.y < 1600 && touch.x > 0 && touch.x < 100){
                oiia.play();
                oiia.setVolume(((float)(main.volume/9))/ 100);
                keyboard.setNull();
                lenh = 1;
                main.setScreen(main.justScreen);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        int yu = 0;
        batch.begin();


        batch.draw(d11,0,0,900,1600);


        batch.draw(bk,0,1500,100,100);
        if (main.language == 0) {


        keyboard.draw(batch);}
        else{
            keyboard.drawRus(batch);
        }
        if (Gdx.input.justTouched()){
            if (main.language == 0){

            if (keyboard.touch(touch.x, touch.y,main.volume)) {
                text = keyboard.getText();

                System.out.println(text);
                if (text.length() != 5){
                    lenh = 1;

                }
                else{
                    main.word = text;
                    keyboard.setNull();
                    oiia.play();
                    oiia.setVolume(((float)(main.volume/9))/100);
                    lenh = 0;
                    main.fourthScreen.myList = new ArrayList<>();
                    main.setScreen(main.fourthScreen);
                }
            }}
            else{
                if (keyboard.touchRus(touch.x, touch.y,main.volume)) {
                    text = keyboard.getText();

                    System.out.println(text);
                    if (text.length() != 5){

                        lenh = 1;

                    }
                    else{
                        main.word = text;
                        keyboard.setNull();
                        oiia.play();
                        oiia.setVolume(((float)(main.volume/9))/100);
                        lenh = 0;
                        main.fourthScreen.myList = new ArrayList<>();
                        main.setScreen(main.fourthScreen);
                    }
                }
            }
        }
        if (main.language == 0){
            if (lenh == 1){
                print("length must be 5",0,1000,900,900/16,batch);
            }
        }
        else{
            if (lenh == 1){
                print("длинна должна быть 5",0,1000,900,900/20,batch);
            }
        }
        print(keyboard.getText(), 450-(keyboard.getText().length() * 100)/2f,600,keyboard.getText().length() * 100,100,batch);
        //joj = new Texture(text.charAt(0)+".png");
        //batch.draw(joj,100,1200,100,100);







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
        keyboard.dispose();
        d11.dispose();
        bk.dispose();
        oiia.dispose();
        batch.dispose();

    }
}
