package ru.words;

import com.google.gson.annotations.SerializedName;
import java.util.Comparator;

public class Data {
    @SerializedName("id")
    int id;
    @SerializedName("Name")
    String name;
    @SerializedName("Score")
    int score;

    public Data(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
