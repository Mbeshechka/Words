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
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaderBoard implements  Screen{
    Main main;
    private Texture d12,bk,nm;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public int volumes,a,id2,score2,sizs;
    public String name2;
    Start_game startGame;

    public Music oiia;
    public volatile List<Data> db = new ArrayList<>();
    float timeCount = 0;
    final float timerInterval = 0.5f;
    public void LoadDB(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://host1879568.hostland.pro/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//        db.add(new Data(24, "Tesla", 30));
//        db.add(new Data(234, "Tesla2", 3));
//        db.add(new Data(245, "Tesla3", 33));


        MyApi myApi = retrofit.create(MyApi.class);
        synchronized (db){
            db = new ArrayList<>();
        }
        myApi.send("leaderboard").enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                synchronized (db) {
                    db = response.body();
                    SortDB();
                    PrintDB();
                    synchronized (db) {
                        if (db.size() > 10) {
                            sizs = 10;
                        } else {
                            sizs = db.size();
                        }
                        startGame.db2 = db;
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                System.out.println("Error getting from db");
                t.printStackTrace();

            }
        });
    }
    public void LoadDB2(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://host1879568.hostland.pro/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//        db.add(new Data(24, "Tesla", 30));
//        db.add(new Data(234, "Tesla2", 3));
//        db.add(new Data(245, "Tesla3", 33));


        MyApi myApi = retrofit.create(MyApi.class);
        synchronized (db){
            db = new ArrayList<>();
        }
        myApi.send(main.name,main.score).enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                synchronized (db) {
                    db = response.body();
                    SortDB();
                    PrintDB();
                    synchronized (db) {
                        if (db.size() > 10) {
                            sizs = 10;
                        } else {
                            sizs = db.size();
                        }
                        startGame.db2 = db;
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                System.out.println("Error getting from db");
                t.printStackTrace();

            }
        });
    }

    public void SortDB(){
//        for (int i = 0; i < db.size(); i++) {
//            for (int j = 0; j < db.size()-1-i; j++) {
//                if(db.get(j + 1).score > db.get(j).score){
//                    Data dat = new Data(db.get(j).id,db.get(j).name,db.get(j).score);
//                    db.get(j).score = db.get(j+1).score;
//                    db.get(j).name = db.get(j+1).name;
//                    db.get(j).id = db.get(j+1).id;
//                    db.get(j+1).id = dat.id;
//                    db.get(j+1).name = dat.name;
//                    db.get(j+1).score = dat.score;
//                }
//            }
//        }
        synchronized (db) {
            db.sort(new Comparator<Data>() {
                @Override
                public int compare(Data data, Data t1) {
                    return t1.score - data.score;
                }
            });
        }

    }
    public void PrintDB(){
        synchronized (db){
        if (db!=null){
            for (Data a:
                db) {
                System.out.println(a.id+" "+a.name+" "+a.score);
            }
        }
        }
    }

    public void print(String word, int x, int y, int width, int height,SpriteBatch batch) {

        Texture letter;
        for (int i = 0; i < word.length(); i++) {
            letter = new Texture(word.toUpperCase().charAt(i) + ".png");
            batch.draw(letter, x + ((float) width / word.length()) * i, y, (float) width / word.length(), height);
        }
    }


    public LeaderBoard(Main main, Start_game st){
        this.main = main;

        volumes = main.volume;
        oiia  = main.cl;
        startGame = st;
        a = 0;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        d12 = new Texture("bg.jpg");
        bk = new Texture("back.png");
//        LoadDB();
//        SortDB();


    }
    @Override
    public void show() {
        PrintDB();


    }

    @Override
    public void render(float delta) {
        timeCount += delta;

        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y> 1500 && touch.y < 1600 && touch.x > 0 && touch.x < 100){
                oiia.play();
                oiia.setVolume(((float)(main.volume/9))/ 100);
                timeCount = 0;
                main.setScreen(main.justScreen);
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d12,0,0,900,1600);

        batch.draw(bk,0,1500,100,100);
        print("name",200,1400,200,50,batch);
        print("score",600,1400,200,50,batch);

        if (timeCount >= timerInterval){

            synchronized (db) {
                if (!db.isEmpty()) {


//                        SortDB();`

                    for (int i = 0; i < sizs; i++) {
                        print(db.get(i).name, 200, 1400 - 100 * (i + 1), db.get(i).name.length() * 50, 50, batch);
                        print(String.valueOf(db.get(i).score), 600, 1400 - 100 * (i + 1), String.valueOf(db.get(i).score).length() * 50, 50, batch);
                        print(String.valueOf(i + 1), 100, 1400 - 100 * (i + 1), String.valueOf(i + 1).length() * 50, 50, batch);
                    }
                }
            }
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
        d12.dispose();
        bk.dispose();
        oiia.dispose();
        batch.dispose();

    }
}
