package ru.words;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeyBoard {
    int width,height;
    private Vector3 touch;
    public Music cl;
    private Map<String,float[]> dict = new HashMap<String,float[]>() ;
    private Map<String,float[]> dict2 = new HashMap<String,float[]>() ;
    private String s = new String("QWERTYUIOPASDFGHJKLZXCVBNM");
    private String s2 = "ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЁЧСМИТЪЬБЮ";
    private Texture t;
    String text = "";
    String text2 = "";
    float[] fl;


    public KeyBoard(int width, int height) {

        this.width = width;
        this.height = height;
        cl = Gdx.audio.newMusic(Gdx.files.internal("kn4.mp3"));
        puts();
    }
    public KeyBoard(int width, int height, int rus) {

        this.width = width;
        this.height = height;

        cl = Gdx.audio.newMusic(Gdx.files.internal("kn4.mp3"));
        putsRus();
    }
    private void puts(){
        for (int i = 0; i < 10; i++) {
            fl = new float[]{ (float) width/10f*i,(float) height/3f*2+200, (float) width/10f*i + (float) width/10f,(float) height/3f*2 +200+ (float) height/3f};

            dict.put(String.valueOf(s.charAt(i)),fl);
        }
        for (int i = 0; i < 9; i++) {
            fl = new float[]{ (width/10f*(i+0)),height/3f*1+200,width/10f*(i+0) + width/10f,height/3f*1 + height/3f+200};
            dict.put(String.valueOf(s.charAt(i+10)),fl);
        }
        for (int i = 0; i < 7; i++) {
            fl = new float[]{ (width/10f*(i+0.5f)),200,(width/10f*(i+0.5f)) + width/10f, height/3f+200};
            dict.put(String.valueOf(s.charAt(i+19)),fl);
        }

    }
    private void putsRus(){
        for (int i = 0; i < 12; i++) {
            fl = new float[]{ (float) width/12f*i,(float) height/3f*2+200, (float) width/12f*i + (float) width/12f,(float) height/3f*2+200 + (float) height/3f};

            dict2.put(String.valueOf(s2.charAt(i)),fl);
        }
        for (int i = 0; i < 11; i++) {
            fl = new float[]{ (width/12f*(i+0)),height/3f*1+200,width/12f*(i+0) + width/12f,height/3f*1 + height/3f+200};
            dict2.put(String.valueOf(s2.charAt(i+12)),fl);
        }
        for (int i = 0; i < 9; i++) {
            fl = new float[]{ (width/12f*(i+0.5f)),200,width/12f*i + width/12f, height/3f+200};
            dict2.put(String.valueOf(s2.charAt(i+23)),fl);
        }
    }
    public void drawRus(SpriteBatch batch){

            for (int i = 0; i < 12; i++) {
                t = new Texture("box.png");
                batch.draw(t,width/12f*i,height/3f*2+200,width/12f,height/3f);
                t = new Texture(s2.charAt(i)+".png");
                batch.draw(t,width/12f*i,height/3f*2+200,width/12f,height/3f);

            }
        for (int i = 0; i < 11; i++) {
            t = new Texture("box.png");
            batch.draw(t,width/12f*i,height/3f*1+200,width/12f,height/3f);
            t = new Texture(s2.charAt(i+12)+".png");
            batch.draw(t,width/12f*i,height/3f*1+200,width/12f,height/3f);
        }
        for (int i =0;i < 9; i++){
            t = new Texture("box.png");
            batch.draw(t,width/12f*(i+0.5f),200,width/12f,height/3f);
            t = new Texture(s2.charAt(i+23)+".png");
            batch.draw(t,width/12f*(i+0.5f),200,width/12f,height/3f);
        }
        t = new Texture("box.png");
        batch.draw(t,width/12f*(9+0.5f),height/3f*0+200,width/12f*2.5f,height/3f);
        t = new Texture("box.png");
        batch.draw(t,width/12f*(11),height/3f*1+200,width/12f,height/3f);
        t = new Texture("Nope.png");
        batch.draw(t,width/12f*(11),height/3f*1+200,width/12f,height/3f);
        t = new Texture("Enter.png");
        batch.draw(t,width/12f*(9+0.5f),height/3f*0+200,width/10f*2.5f,height/3f);
    }public void drawGameRus(SpriteBatch batch,Map<String,Integer> dg){

        for (int i = 0; i < 12; i++) {
            if (dg.get(String.valueOf(s2.charAt(i))) == 0){
                t = new Texture("box.png");
            } else if (dg.get(String.valueOf(s2.charAt(i))) == 1){
                t = new Texture("yellow.png");
            }
            else if (dg.get(String.valueOf(s2.charAt(i))) == 2){
                t = new Texture("green.png");
            }
            else if (dg.get(String.valueOf(s2.charAt(i))) == 3){
                t = new Texture("gray.png");
            }

            batch.draw(t,width/12f*i,height/3f*2+200,width/12f,height/3f);
            t = new Texture(s2.charAt(i)+".png");
            batch.draw(t,width/12f*i,height/3f*2+200,width/12f,height/3f);

        }
        for (int i = 0; i < 11; i++) {
            if (dg.get(String.valueOf(s2.charAt(i+12))) == 0){
                t = new Texture("box.png");
            } else if (dg.get(String.valueOf(s2.charAt(i+12))) == 1){
                t = new Texture("yellow.png");
            }
            else if (dg.get(String.valueOf(s2.charAt(i+12))) == 2){
                t = new Texture("green.png");
            }
            else if (dg.get(String.valueOf(s2.charAt(i+12))) == 3){
                t = new Texture("gray.png");
            }
            batch.draw(t,width/12f*(i+0),height/3f*1+200,width/12f,height/3f);
            t = new Texture(s2.charAt(i+12)+".png");
            batch.draw(t,width/12f*(i+0),height/3f*1+200,width/12f,height/3f);

        }
        t = new Texture("box.png");
        batch.draw(t,width/12f*(9+0.5f),height/3f*0+200,width/12f*2.5f,height/3f);
        t = new Texture("box.png");
        batch.draw(t,width/12f*(11),height/3f*1+200,width/12f,height/3f);
        t = new Texture("Nope.png");
        batch.draw(t,width/12f*(11),height/3f*1+200,width/12f,height/3f);
        t = new Texture("Enter.png");
        batch.draw(t,width/12f*(9+0.5f),height/3f*0+200,width/10f*2.5f,height/3f);


        for (int i = 0; i < 9; i++) {
            if (dg.get(String.valueOf(s2.charAt(i+23))) == 0){
                t = new Texture("box.png");
            } else if (dg.get(String.valueOf(s2.charAt(i+23))) == 1){
                t = new Texture("yellow.png");
            }
            else if (dg.get(String.valueOf(s2.charAt(i+23))) == 2){
                t = new Texture("green.png");
            }
            else if (dg.get(String.valueOf(s2.charAt(i+23))) == 3){
                t = new Texture("gray.png");
            }
            batch.draw(t,width/12f*(i+0.5f),200,width/12f,height/3f);
            t = new Texture(s2.charAt(i+23)+".png");
            batch.draw(t,width/12f*(i+0.5f),200,width/12f,height/3f);

        }
    }

    public boolean touchRus(float x, float y,int volume){
        text2 = text;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 1; j++) {

            }
            if(x>dict2.get(String.valueOf(s2.charAt(i)))[0] && x<dict2.get(String.valueOf(s2.charAt(i)))[2] &&
                y > dict2.get(String.valueOf(s2.charAt(i)))[1] && y < dict2.get(String.valueOf(s2.charAt(i)))[3] )
            {
                cl.play();
                cl.setVolume(volume/900f);
                text += String.valueOf(s2.charAt(i));
                return false;
            }
        }
        if (text2 == text) {
            if (x < width && x>width/12f*9.5f && y<height/3f+200 && y > 200){
                return true;

            }
            else if(x < width && x>width/12f*(11) && y<height/3f*2+200 && y > height/3f*1 +200&& !text.isEmpty()){
                if (text.length() == 1){
                    text = "";
                }
                else{
                    StringBuilder gdxSb = new StringBuilder(text);
                    text = gdxSb.substring(0,text.length()-1);
                }
            }


        }
        return false;
    }
    public boolean touchRusGame(float x, float y,int volume){
        text2 = text;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 1; j++) {

            }
            if(x>dict2.get(String.valueOf(s2.charAt(i)))[0] && x<dict2.get(String.valueOf(s2.charAt(i)))[2] &&
                y > dict2.get(String.valueOf(s2.charAt(i)))[1] && y < dict2.get(String.valueOf(s2.charAt(i)))[3] )
            {
                cl.play();
                cl.setVolume(volume/900f);
                if(text.length() != 5){
                text += String.valueOf(s2.charAt(i));}
                return false;
            }
        }
        if (text2 == text) {
            if (x < width && x>width/12f*9.5f && y<height/3f+200 && y > 200){
                return true;

            }
            else if(x < width && x>width/12f*(11) && y<height/3f*2+200 && y > height/3f*1+200 && !text.isEmpty()){
                if (text.length() == 1){
                    text = "";
                }
                else{
                    StringBuilder gdxSb = new StringBuilder(text);
                    text = gdxSb.substring(0,text.length()-1);
                }
            }


        }
        return false;
    }
    public void draw(SpriteBatch batch){
        for (int i = 0; i < 10; i++) {
            t = new Texture("box.png");
            batch.draw(t,width/10f*i,height/3f*2+200,width/10f,height/3f);
            t = new Texture(s.charAt(i)+".png");
            batch.draw(t,width/10f*i,height/3f*2+200,width/10f,height/3f);

        }
        for (int i = 0; i < 9; i++) {
            t = new Texture("box.png");
            batch.draw(t,width/10f*(i+0),height/3f*1+200,width/10f,height/3f);
            t = new Texture(s.charAt(i+10)+".png");
            batch.draw(t,width/10f*(i+0),height/3f*1+200,width/10f,height/3f);

        }
        t = new Texture("box.png");
        batch.draw(t,width/10f*(7+0.5f),height/3f*0+200,width/10f*2.5f,height/3f);
        t = new Texture("box.png");
        batch.draw(t,width/10f*(9),height/3f*1+200,width/10f,height/3f);
        t = new Texture("Nope.png");
        batch.draw(t,width/10f*(9),height/3f*1+200,width/10f,height/3f);
        t = new Texture("Enter.png");
        batch.draw(t,width/10f*(7+0.5f),height/3f*0+200,width/10f*2.5f,height/3f);



        for (int i = 0; i < 7; i++) {
            t = new Texture("box.png");
            batch.draw(t,width/10f*(i+0.5f),200,width/10f,height/3f);
            t = new Texture(s.charAt(i+19)+".png");
            batch.draw(t,width/10f*(i+0.5f),200,width/10f,height/3f);

        }






    }
    public boolean touch(float x, float y,int volume){
        text2 = text;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 1; j++) {

            }
            if(x>dict.get(String.valueOf(s.charAt(i)))[0] && x<dict.get(String.valueOf(s.charAt(i)))[2] &&
                y > dict.get(String.valueOf(s.charAt(i)))[1] && y < dict.get(String.valueOf(s.charAt(i)))[3] )
            {
                cl.play();
                cl.setVolume(volume/900f);
                text += String.valueOf(s.charAt(i));
                return false;
            }
        }
        if (text2 == text) {
            if (x < width && x>width/10f*7.5f && y<height/3f+200 && y > 200){
                return true;

            }
            else if(x < width && x>width/10f*(8+0.5f) && y<height/3f*2 +200&& y > height/3f*1+200 && !text.isEmpty()){
                if (text.length() == 1){
                    text = "";
                }
                else{
                    StringBuilder gdxSb = new StringBuilder(text);
                    text = gdxSb.substring(0,text.length()-1);
                }
            }


        }
        return false;
    }
    public boolean touchGame(float x, float y,int volume){
        text2 = text;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 4; j++) {

            }
            if(x>dict.get(String.valueOf(s.charAt(i)))[0] && x<dict.get(String.valueOf(s.charAt(i)))[2] &&
                y > dict.get(String.valueOf(s.charAt(i)))[1] && y < dict.get(String.valueOf(s.charAt(i)))[3] )
            {
                cl.play();
                cl.setVolume(volume/900f);
                if( text.length() !=5){
                    text += String.valueOf(s.charAt(i));
                }
                return false;
            }
        }
        if (text2 == text) {
            if (x < width && x>width/10f*(7+0.5f) && y<height/3f*1+200 && y > 200){
                return true;

            }
            else if(x < width && x>width/10f*(8+0.5f) && y<height/3f*2+200 && y > height/3f*1 +200&& !text.isEmpty()){
                if (text.length() == 1){
                    text = "";
                }
                else{
                    StringBuilder gdxSb = new StringBuilder(text);
                    text = gdxSb.substring(0,text.length()-1);
                }
            }


        }
        return false;
    }
    public String getText(){
        return text;
    }
    public void setNull(){
        text = "";
    }
    public void dispose(){
        t.dispose();

    }
    public void drawGame(SpriteBatch batch,Map<String,Integer> dg){
        for (int i = 0; i < 10; i++) {
            if (dg.get(String.valueOf(s.charAt(i))) == 0){
                t = new Texture("box.png");
            } else if (dg.get(String.valueOf(s.charAt(i))) == 1){
                t = new Texture("yellow.png");
            }
            else if (dg.get(String.valueOf(s.charAt(i))) == 2){
                t = new Texture("green.png");
            }
            else if (dg.get(String.valueOf(s.charAt(i))) == 3){
                t = new Texture("gray.png");
            }

            batch.draw(t,width/10f*i,height/3f*2+200,width/10f,height/3f);
            t = new Texture(s.charAt(i)+".png");
            batch.draw(t,width/10f*i,height/3f*2+200,width/10f,height/3f);

        }
        for (int i = 0; i < 9; i++) {
            if (dg.get(String.valueOf(s.charAt(i+10))) == 0){
                t = new Texture("box.png");
            } else if (dg.get(String.valueOf(s.charAt(i+10))) == 1){
                t = new Texture("yellow.png");
            }
            else if (dg.get(String.valueOf(s.charAt(i+10))) == 2){
                t = new Texture("green.png");
            }
            else if (dg.get(String.valueOf(s.charAt(i+10))) == 3){
                t = new Texture("gray.png");
            }
            batch.draw(t,width/10f*(i+0),height/3f*1+200,width/10f,height/3f);
            t = new Texture(s.charAt(i+10)+".png");
            batch.draw(t,width/10f*(i+0),height/3f*1+200,width/10f,height/3f);

        }
        t = new Texture("box.png");
        batch.draw(t,width/10f*(7+0.5f),height/3f*0+200,width/10f*2.5f,height/3f);
        t = new Texture("box.png");
        batch.draw(t,width/10f*(9),height/3f*1+200,width/10f,height/3f);
        t = new Texture("Nope.png");
        batch.draw(t,width/10f*(9),height/3f*1+200,width/10f,height/3f);
        t = new Texture("Enter.png");
        batch.draw(t,width/10f*(7+0.5f),height/3f*0+200,width/10f*2.5f,height/3f);



        for (int i = 0; i < 7; i++) {
            if (dg.get(String.valueOf(s.charAt(i+19))) == 0){
                t = new Texture("box.png");
            } else if (dg.get(String.valueOf(s.charAt(i+19))) == 1){
                t = new Texture("yellow.png");
            }
            else if (dg.get(String.valueOf(s.charAt(i+19))) == 2){
                t = new Texture("green.png");
            }
            else if (dg.get(String.valueOf(s.charAt(i+19))) == 3){
                t = new Texture("gray.png");
            }
            batch.draw(t,width/10f*(i+0.5f),200,width/10f,height/3f);
            t = new Texture(s.charAt(i+19)+".png");
            batch.draw(t,width/10f*(i+0.5f),200,width/10f,height/3f);

        }






    }
}
