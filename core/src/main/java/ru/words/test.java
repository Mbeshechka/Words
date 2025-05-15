package ru.words;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args){


//        System.out.println((new float[]{ (width/10f),height/3f*2,width/10f + width/10f,height/3f*2 + height/3f})[3]);
//        System.out.println(dict.toString());
//        for (int i = 0; i < 26; i++)

        // Читаем все строки
        FileHandle file = Gdx.files.external("your data.txt");

        // Или для локального хранилища (не требует разрешений)
        // FileHandle file = Gdx.files.local(filename);

        file.writeString("tt", false);

    }


//        }


}
