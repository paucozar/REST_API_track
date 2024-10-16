package com.example.retrofitrecyclerview.network;
// ApiService.java
import com.example.retrofitrecyclerview.model.Track;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface APITrack {
    @GET("tracks")
    Call<List<Track>> getTracks();

    @POST("tracks")
    Call<Track> addTrack(@Body Track track);

    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") int id);
}

