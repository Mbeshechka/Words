package ru.words;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class fourth_Screen implements  Screen{
    Main main;
    float timeCount = 0;
    final float timerInterval = 1.5f;
    private Texture d12,bk,book,box;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    String img;
    public int volumes,oi=0;
    private KeyBoard keyboard;
    Map<String,Integer> dict = new HashMap<>() ;
    Map<String,Integer> dg = new HashMap<>() ;
    private String s = "QWERTYUIOPASDFGHJKLZXCVBNM";
    String s2 = "ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЁЧСМИТЪЬБЮ";
    BitmapFont font70;
    String text,word;
    int times = 6;
    public List<String> myList,ifn;
    int[] ai = new int[5];

    public Music oiia;
    public fourth_Screen(Main main){
        this.main = main;

        main.end = false;

        font70 = new BitmapFont(Gdx.files.internal("stylo90gray.fnt"));
        keyboard = new KeyBoard( 900, 300);
        volumes = main.volume;

        oiia = main.cl;
        myList = new ArrayList<>();
        ifn = new ArrayList<>();
        word = main.word;
        text = main.word;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        d12 = new Texture("bg.jpg");
        box = new Texture("box.png");
        bk = new Texture("back.png");


    }

    public int count(String sw, String sw2){
        int o = 0;
        for (int i = 0; i < sw.length(); i++) {
            if (Objects.equals(String.valueOf(sw.charAt(i)), sw2)){
                o ++;
            }
        }
        return o ;

    }
    public void puts() {
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf(s.charAt(i)),count(main.word,String.valueOf(s.charAt(i))));
        }

    }
    public void putsRus() {
        for (int i = 0; i < 32; i++) {
            dict.put(String.valueOf(s2.charAt(i)),count(main.word,String.valueOf(s2.charAt(i))));
        }

    }
    public void puts2() {
        for (int i = 0; i < 26; i++) {
            dg.put(String.valueOf(s.charAt(i)),0);
        }

    }
    public void puts2Rus() {
        for (int i = 0; i < 32; i++) {
            dg.put(String.valueOf(s2.charAt(i)),0);
        }

    }
    @Override
    public void show() {
        if (main.language == 0){
            keyboard = new KeyBoard( 900, 300);
        }else{
            keyboard = new KeyBoard( 900, 300,1);
        }

    }
    public void print(String word, float x, float y, float width, float height,SpriteBatch batch) {
        Texture letter;
        String alp = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЁЧСМИТЪЬБЮ";
        for (int i = 0; i < word.length(); i++) {
            if (alp.contains(String.valueOf(word.toUpperCase().charAt(i)))){
                letter = new Texture(word.toUpperCase().charAt(i) + ".png");
                batch.draw(letter, x + ( width /(float) word.length()) * i, y,  width / (float) word.length(), height);
            }
        }
    }



    @Override
    public void render(float delta) {
        main.endGameScreen.gm = 0;
        oi++;
        if (oi == 1){
            if (main.language == 0){
                puts2();
            }
            else{
                puts2Rus();
            }
        }


        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y> 1500 && touch.y < 1600 && touch.x > 0 && touch.x < 100){
                oiia.play();
                oiia.setVolume((main.volume/9f)/ 100f);
                myList = ifn;
                keyboard.setNull();
                times = 6;
                main.setScreen(main.justScreen);
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(d12,0,0,900,1600);

        batch.draw(bk,0,1500,100,100);

        if (main.language == 0){
            keyboard.drawGame(batch,dg);
        }
        else{
            keyboard.drawGameRus(batch,dg);
        }
        if  (Gdx.input.justTouched()){
            if (main.language == 0){
            if (keyboard.touchGame(touch.x, touch.y,main.volume) && main.game == 0){
                if (keyboard.getText().length() == 5){
                    text = keyboard.getText().toUpperCase();
                    keyboard.setNull();
                    times -= 1;
                    myList.add(text);
                }

            }}
            else{
                if (keyboard.touchRusGame(touch.x, touch.y,main.volume) && main.game == 0){
                    if (keyboard.getText().length() == 5){
                        text = keyboard.getText().toUpperCase();
                        keyboard.setNull();
                        times -= 1;
                        myList.add(text);
                    }

                }
            }
        }



        if (!myList.isEmpty()){
            for (int i = 0; i < myList.size(); i++) {
                if (main.language == 0){
                    puts();
                }
                else{
                    putsRus();
                }
                ai[0] = 0;
                ai[1] = 0;
                ai[2] = 0;
                ai[3] = 0;
                ai[4] = 0;
                for (int j = 0; j < 5; j++) {
                    if (myList.get(i).charAt(j) == main.word.charAt(j)){
                        dg.put(String.valueOf(myList.get(i).charAt(j)),2);
                        ai[j] = 1;
                        dict.put(String.valueOf(myList.get(i).charAt(j)),
                            dict.get(String.valueOf(myList.get(i).charAt(j)))-1) ;
                        img = "green.png";
                        System.out.println(String.valueOf(myList.get(i).charAt(j)));
                        book = new Texture(img);
                        batch.draw(book,200 + 100 * j,1400 - 100 * (i),100,100);

                    }}
                    for (int j = 0; j < 5; j++) {
                        if(main.word.contains(String.valueOf(myList.get(i).charAt(j))) && ai[j] != 1 && dict.get(String.valueOf(myList.get(i).charAt(j))) != 0 ){
                            ai[j] = 1;
                            if(dg.get(String.valueOf(myList.get(i).charAt(j))) != 2){
                                dg.put(String.valueOf(myList.get(i).charAt(j)),1);
                            }
                            dict.put(String.valueOf(myList.get(i).charAt(j)),
                                dict.get(String.valueOf(myList.get(i).charAt(j)))-1) ;
                            img = "yellow.png";
                            book = new Texture(img);
                            batch.draw(book,200 + 100 * j,1400 - 100 * (i),100,100);
                        }}
                for (int j = 0; j < 5; j++) {
                    if(ai[j] != 1){
                        if(dg.get(String.valueOf(myList.get(i).charAt(j))) != 2 &&
                            dg.get(String.valueOf(myList.get(i).charAt(j))) != 1){
                            dg.put(String.valueOf(myList.get(i).charAt(j)),3);
                        }
                            img = "gray.png";
                            book = new Texture(img);
                            batch.draw(book,200 + 100 * j,1400 - 100 * (i),100,100);
                        }
                    }



            }
            for (int i = myList.size(); i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    batch.draw(box,200 + 100 * j,1400 - 100 * (i),100,100);            }
            }
            for (int i = 0; i < myList.size(); i++) {
                for (int j = 0; j < 5; j++) {
                    img = myList.get(i).charAt(j) + ".png";
                    book = new Texture(img);
                    batch.draw(book,200 + 100 * j ,1400 - (100 * i),100,100);

                }
            }
        }
        else{
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    batch.draw(box,200 + 100 * j,900 + 100 * i,100,100);            }
            }
        }
        if (!myList.isEmpty() ){
            if(Objects.equals(myList.get(myList.size() - 1), main.word)){
                main.end = true;
                timeCount += delta;
                main.game=1;
                if (timeCount >= timerInterval){
                    timeCount = 0f;
                    myList = ifn;
                    if(main.language == 0){
                        puts2();
                        puts();
                    }else{
                        puts2Rus();
                        putsRus();
                    }
                    main.setScreen(main.endGameScreen);
                }
            }
            else if(myList.size() == 6){
                main.end = false;


                timeCount += delta;
                main.game=1;
                if (timeCount >= timerInterval){
                    timeCount = 0f;
                    if(main.language == 0){
                        puts2();
                        puts();
                    }else{
                        puts2Rus();
                        putsRus();
                    }

                    main.setScreen(main.endGameScreen);
                }

            }
        }
        print(keyboard.text.toUpperCase(), 200,1400 -  100 * (myList.size())
            ,keyboard.text.toUpperCase().length() * 100,100,batch);








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
        d12.dispose();
        bk.dispose();
        book.dispose();
        oiia.dispose();
        batch.dispose();
        box.dispose();

    }
}
