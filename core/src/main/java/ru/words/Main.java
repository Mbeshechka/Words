package ru.words;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final float SCREEN_WIDTH = 900;
    public static final float SCREEN_HEIGHT = 1600;

    public SpriteBatch batch;
    public volatile List<Data> db3 = new ArrayList<>();
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public Boolean random_word,end ;
    first_screen firstScreen;
    second_Screen secondScreen;
    just_Screen justScreen;
    third_Screen thirdScreen;
    fourth_Screen fourthScreen;
    Start_game startGame,startGame2;
    End_game_screen endGameScreen;
    LeaderBoard led;
    public int whichSc,volume,score,game;
    String fileString2;

    public String word,name;
    public List<String> myList;
    public Music cl;






    @Override
    public void create() {

        cl = Gdx.audio.newMusic(Gdx.files.internal("kn3.wav"));
        myList = new ArrayList<>();
        end = false;
        List<String> fileStrings = new ArrayList<>();
        String fileString = Gdx.files.internal("sl.txt").readString();
        String[] Arr;
        Arr = fileString.split("\n");
        for (int i = 0; i < Arr.length; i++) {
            if (Arr[i].length() == 5 && !Arr[i].contains("'")&& !Arr[i].contains(".") && !Arr[i].contains("-") && !Arr[i].contains(",")  && Arr[i].charAt(0) == Arr[i].toLowerCase().charAt(0)) {
                myList.add(Arr[i].toUpperCase());
            }
        }





        Collections.shuffle(myList);
        //System.out.println(myList);
        word = myList.get(0);


        System.out.println(word);
        volume = 891-9;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);
        random_word = false;
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("stylo90gray.fnt"));
        firstScreen = new first_screen(this);
        secondScreen = new second_Screen(this);
        justScreen = new just_Screen(this);
        thirdScreen = new third_Screen(this);
        fourthScreen = new fourth_Screen(this);
        startGame2 = new Start_game(this);
        endGameScreen = new End_game_screen(this);
        led = new LeaderBoard(this,startGame2);
        startGame = startGame2;


        fileString2 = Gdx.files.internal("your data.txt").readString();
        System.out.println(fileString2);

        if (fileString2.isEmpty()){
            led.LoadDB();
        }
        else {
            score = Integer.parseInt(fileString2.split(" ")[1]);
            name = fileString2.split(" ")[0];
        }
        whichSc = 0;
        fileString2 = Gdx.files.internal("your data.txt").readString();
        System.out.println(fileString2);

        if (fileString2.isEmpty()){
            setScreen(startGame);
        }
        else{
            setScreen(justScreen);
        }



    }



    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        cl.dispose();

    }
}
