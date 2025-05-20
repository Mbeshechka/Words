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
    String s = "вам надо отгадывать слова из 5 букв " +
        "на это дается 6 попыток в уже введенных словах" +
        " буквы перекрашиваются в один из трех цветов " +
        "если в зеленый то в загаданном слове эта буква стоит " +
        "на этой же позиции если в желтый то эта буква есть в загаданном " +
        "слове но она стоит на другом месте если в серый " +
        "то этой буквы нету в загаданном слове";
    String s2 = "you need to guess words of 5 letters" +
        " this is given 6 attempts. in already entered words" +
        " the letters are repainted in one of three colors" +
        " If in green then in the hidden word this letter is " +
        "in the same position if in yellow" +
        " then this letter is in the hidden word" +
        " but it is in a different place if in gray" +
        " then this letter is not in the hidden word";

    public Music oiia;
    public third_Screen(Main main){
        this.main = main;
        System.out.println(main.SCREEN_WIDTH);
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
    public void print2(String word, float x, float y, float width, float height,SpriteBatch batch) {
        Texture letter;
        int d = -1;
        String alp = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890ЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
        for (int i = 0; i < word.length(); i++) {
            d ++;
            if (alp.contains(String.valueOf(word.toUpperCase().charAt(i))) && x + (width)*(d+1) < main.SCREEN_WIDTH){
                letter = new Texture(word.toUpperCase().charAt(i) + ".png");
                batch.draw(letter, x + ( width) * d, y,  width , height);
            }
            else if((x + (width)*(d+1) > main.SCREEN_WIDTH || x+width == main.SCREEN_WIDTH) && alp.contains(String.valueOf(word.toUpperCase().charAt(i))) ){
                x =0;
                y -= (height*1.6);
                d=0;
                letter = new Texture(word.toUpperCase().charAt(i) + ".png");
                batch.draw(letter, x + ( width ) * d, y,  width , height);

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
                oiia.setVolume((main.volume/9f)/ 100f);
                main.setScreen(main.justScreen);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d1,0,0,900,1600);

        batch.draw(bk,0,1500,100,100);
        if (main.language == 0){
            print2(s2,0,1400, (float) Math.pow((double)(1400*900/345/1.6),(double)0.5),(float) Math.pow((double)(1400*900/345/1.6),(double)0.5),batch);

        }
        else{
            print2(s,0,1400, (float) Math.pow((double)(1400*900/s.length()/1.6),(double)0.5),(float) Math.pow((double)(1400*900/s.length()/1.6),(double)0.5),batch);


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
