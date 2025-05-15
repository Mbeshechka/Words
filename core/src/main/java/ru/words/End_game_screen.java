package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class End_game_screen implements  Screen{
    button btn;
    button btn2;
    button btn3;
    button btn4;
    button btn5;
    button btn6;
    Main main;
    private Texture d1,bk,ab;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public int volumes,gm,a;


    public Music oiia;
    public End_game_screen(Main main){
        this.main = main;
        oiia = main.cl;
        volumes = main.volume;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        btn6 = new button(font,"Назад",0,1600);
        btn = new button(font,"second_screen",150,1000);
        btn2 = new button(font,"first_screen",150,1300);
        btn3 = new button(font,"third_screen",150,700);
        btn4 = new button(font,"fourth_screen",150,400);
        d1 = new Texture("bg.jpg");
        bk = new Texture("back.png");
        ab = new Texture("about.png");


    }
    @Override
    public void show() {
        a = 0;

    }
    public void print(String word, float x, float y, float width, float height,SpriteBatch batch) {
        Texture letter;
        String alp = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        for (int i = 0; i < word.length(); i++) {
            if (alp.contains(String.valueOf(word.toUpperCase().charAt(i)))){
                letter = new Texture(word.toUpperCase().charAt(i) + ".png");
                batch.draw(letter, x + ( width /(float) word.length()) * i, y,  width / (float) word.length(), height);
            }
        }
    }

    @Override
    public void render(float delta) {
        main.fourthScreen.myList = new ArrayList<>();
        main.fourthScreen.oi = 0;

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d1,0,0,900,1600);

        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.x >150&&touch.x<750&&touch.y>1000&&touch.y<1050){
                oiia.play();
                oiia.setVolume(main.volume/900f);
                main.setScreen(main.justScreen);
            }
            else if (touch.x >200&&touch.x<700&&touch.y>800&&touch.y<850){
                oiia.play();
                oiia.setVolume(main.volume/900f);
                if (main.random_word){
                    gm = 1;
                    Collections.shuffle(main.myList);
                    //System.out.println(myList);
                    main.word = main.myList.get(0);
                    main.setScreen(main.fourthScreen);
                }else{
                    main.setScreen(main.secondScreen);
                }
            }

        }
        a++;
        if (a==1){
            if (main.random_word){
                main.score += 1;
            }
            try (FileWriter writer = new FileWriter("your data.txt", false)) {  // false - перезапись файла
                writer.write(main.name+" "+main.score);
            } catch (IOException e) {
            }
            main.led.LoadDB2();

        }
         main.game = 0;
        if (main.end && gm != 1 ){

            print("You won",100,1200,700,100,batch);
            print("back to menu",150,1000,600,50,batch);
            print("play again",200,800,500,50,batch);
        }
        else if(gm!=1){
            main.fourthScreen.myList = main.fourthScreen.ifn;
            print("You Lost",50,1200,800,100,batch);
            print("back to menu",150,1000,600,50,batch);
            print("play again",200,800,500,50,batch);
            print("the word was",150,600,600,50,batch);
            print(main.word,375,500,250,50,batch);

        }







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
        font.dispose();


    }
}

