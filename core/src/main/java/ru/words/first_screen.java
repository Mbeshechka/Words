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
import java.util.Collections;

public class first_screen implements  Screen{
    Main main;

    private Texture d13,bk,rand,on,off,c1,c2,ny,muz,mus;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public int x1,y1;

    public Music oiia,kn4;
    public first_screen(Main main){

        this.main = main;
        kn4 = Gdx.audio.newMusic(Gdx.files.internal("kn4.mp3"));
        oiia = main.cl;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        d13 = new Texture("bg.jpg");
        bk = new Texture("back.png");
        on = new Texture("ON.png");
        off = new Texture("OFF.png");

        muz = new Texture("muz.png");
        mus = new Texture("beg.png");




    }
    @Override
    public void show() {

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
                oiia.setVolume(((float)(main.volume / 9))/ 100);
                main.setScreen(main.justScreen);
            }
            if (touch.y > 1300 && touch.y < 1400 && touch.x > 800 && touch.x < 900){

                kn4.play();
                kn4.setVolume(((float)(main.volume/9))/ 100);
                main.random_word = !main.random_word;
            }
            if (touch.y > 950 && touch.y < 1050 ){
                main.volume = (int) touch.x;
                y1 = (int) touch.y;
                kn4.play();
                kn4.setVolume(((float)(main.volume/9))/ 100);
            }
            if (touch.x>0&&touch.x<900&&touch.y >550&&touch.y<650){
                if (main.language == 1){
                    main.language = 0;
                    main.myList = new ArrayList<>();
                    String fileString = Gdx.files.internal("sl.txt").readString();
                    String[] Arr;
                    Arr = fileString.split("\n");
                    for (int i = 0; i < Arr.length; i++) {
                        if (Arr[i].length() == 5 && !Arr[i].contains("'")&& !Arr[i].contains(".") && !Arr[i].contains("-") && !Arr[i].contains(",")  && Arr[i].charAt(0) == Arr[i].toLowerCase().charAt(0)) {
                            main.myList.add(Arr[i].toUpperCase());
                        }
                    }





                    Collections.shuffle(main.myList);
                    //System.out.println(myList);
                    main.word = main.myList.get(0);
                }
                else{
                    main.language = 1;
                    main.myList = new ArrayList<>();
                    String fileString = Gdx.files.internal("rus.txt").readString("utf8");
                    String[] Arr;
                    Arr = fileString.split("\n");
                    for (int i = 0; i < Arr.length; i++) {
                        if (Arr[i].length() == 5 && !Arr[i].contains("'")&& !Arr[i].contains(".") && !Arr[i].contains("-") && !Arr[i].contains(",")  && Arr[i].charAt(0) == Arr[i].toLowerCase().charAt(0)) {
                            main.myList.add(Arr[i].toUpperCase());
                        }
                    }





                    Collections.shuffle(main.myList);
                    //System.out.println(myList);
                    main.word = main.myList.get(0);
                }
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d13,0,0,900,1600);
        batch.draw(bk,0,1500,100,100);

        if (main.random_word) {
            batch.draw(on,800,1300,100,100);

        }
        else {
            batch.draw(off,800,1300,100,100);
        }

        batch.draw(muz,-20,950,940,100);
        batch.draw(mus,main.volume,950,20,100);

        if (main.volume / 9 == 100){
            c1 = new Texture("1.png");
            c2 = new Texture("0.png");
            batch.draw(c1,400,900,50,50);
            batch.draw(c2,450,900,50,50);
            batch.draw(c2,500,900,50,50);
        } else if (main.volume / 9 > 9) {
            c1 = new Texture(String.valueOf(main.volume/ 90) + ".png");
            c2 = new Texture(String.valueOf(main.volume / 9 % 10) + ".png");
            batch.draw(c1,450,900,50,50);
            batch.draw(c2,500,900,50,50);


        } else if (main.volume / 9 < 10) {
            c1 = new Texture(String.valueOf(main.volume / 9) + ".png");
            batch.draw(c1,500,900,50,50);
        }
        if(main.language == 0){
            print("random word",0,1300,700,70,batch);
            print("sounds",0,1100,420,70,batch);
            print("language",0,700,600,75,batch);
            print("english",200,550,525,75,batch);
        }
        else{
            print("звуки",0,1100,375,75,batch);
            print("случайное слово",0,1320,700,700/15,batch);
            print("язык",0,700,300,75,batch);
            print("русский",200,550,525,75,batch);
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
        d13.dispose();
        bk.dispose();
        on.dispose();
        off.dispose();
        rand.dispose();
        ny.dispose();
        c1.dispose();
        c2.dispose();
        mus.dispose();
        muz.dispose();
        oiia.dispose();
        batch.dispose();

    }
}
