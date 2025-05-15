package ru.words;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
    @GET("bear.php")
    Call<List<Data>> send(@Query("q") String s);
    @GET("bear.php")
    Call<List<Data>> send(@Query("name") String n,@Query("score") int sc);


}
