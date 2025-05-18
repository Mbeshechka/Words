package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Start_game implements  Screen{
    Main main;
    private Texture d1,bk,ab;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public int volumes;
    KeyBoard keyBoard;
    just_Screen justScreen;
    public volatile List<Data> db2 = new ArrayList<>();

    public Music oiia;
    public Start_game(Main main){
        this.main = main;

        oiia = main.cl;
        justScreen = main.justScreen;
        volumes = main.volume;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        d1 = new Texture("bg.jpg");
        bk = new Texture("back.png");
        ab = new Texture("about.png");

        keyBoard = new KeyBoard(900,300);


    }
    @Override
    public void show() {

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

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d1,0,0,900,1600);
        if (main.koi == 0) {
            keyBoard.draw(batch);
            if (Gdx.input.justTouched()) {
                touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touch);
                if (keyBoard.touch(touch.x, touch.y, 0)) {
                    if (keyBoard.getText().length() < 9 && !keyBoard.getText().isEmpty()) {
                        boolean flag = true;
                        for (int i = 0; i < db2.size(); i++) {
                            System.out.println(keyBoard.getText() + " " + db2.get(i).name.toUpperCase());
                            if (Objects.equals(keyBoard.getText(), db2.get(i).name.toUpperCase())) {
                                flag = false;
                                break;
                            }

                        }
                        System.out.println(flag);
                        if (flag) {
                            main.name = keyBoard.getText();
                            FileHandle file = Gdx.files.internal("your data.txt"); // local - для файлов в приватной директории приложения
                            file.writeString(main.name + " 0", false);
//                        try (FileWriter writer = new FileWriter("your data.txt", false)) {  // false - перезапись файла
//                            writer.write(main.name+" 0");
//                        } catch (IOException e) {
//                        }
                            String fileString2 = Gdx.files.internal("your data.txt").readString();
                            main.score = Integer.parseInt(fileString2.split(" ")[1]);
                            main.name = fileString2.split(" ")[0];

                            main.setScreen(justScreen);


                        }
                    }
                }
            }
            print("Hey whats your name", 0, 1200, 900, 900 / (float) "Hey whats your name".length(), batch);
            print(keyBoard.getText(), 450 - (keyBoard.getText().length() * 100) / 2f, 800, keyBoard.getText().length() * 100, 100, batch);
        }
        else{
            print("cannot conect",0,800,900,900/13,batch);
            print("to server",(900-(900/13*10))/2,800-(900/13),900/13*10,900/13,batch);
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


    }
}
