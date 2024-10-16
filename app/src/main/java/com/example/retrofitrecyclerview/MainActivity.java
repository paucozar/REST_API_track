package com.example.retrofitrecyclerview;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitrecyclerview.model.Track;
import com.example.retrofitrecyclerview.network.TrackAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TrackAdapter.OnTrackClickListener {
    private RecyclerView recyclerView;
    private TrackAdapter trackAdapter;
    private List<Track> trackList = new ArrayList<>();
    private Button addTrackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addTrackButton = findViewById(R.id.addTrackButton);

        trackAdapter = new TrackAdapter(trackList, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trackAdapter);

        addTrackButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTrackActivity.class);
            startActivityForResult(intent, 1);
        });

        loadTracks();
    }

    private void loadTracks() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Track>> call = apiService.getTrack();
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(@NonNull Call<List<Track>> call, @NonNull Response<List<Track>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    trackList.clear();
                    trackList.addAll(response.body());
                    trackAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Track>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTrackClick(Track track) {
        Intent intent = new Intent(MainActivity.this, AddEditTrackActivity.class);
        intent.putExtra("track", track);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadTracks();
        }
    }
}
