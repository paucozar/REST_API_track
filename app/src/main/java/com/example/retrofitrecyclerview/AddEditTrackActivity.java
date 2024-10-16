package com.example.retrofitrecyclerview;

// AddEditTrackActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitrecyclerview.model.Track;
import com.example.retrofitrecyclerview.network.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTrackActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText artistEditText;
    private Button saveButton;
    private Track track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_track);

        titleEditText = findViewById(R.id.titleEditText);
        artistEditText = findViewById(R.id.artistEditText);
        saveButton = findViewById(R.id.saveButton);

        track = (Track) getIntent().getSerializableExtra("track");
        if (track != null) {
            titleEditText.setText(track.getTitle());
            artistEditText.setText(track.getArtist());
        }

        saveButton.setOnClickListener(v -> saveTrack());
    }

    private void saveTrack() {
        String title = titleEditText.getText().toString();
        String artist = artistEditText.getText().toString();

        if (track == null) {
            track = new Track(0, title, artist); // 0 como ID para nuevos tracks
            addTrack(track);
        } else {
            track.setTitle(title);
            track.setArtist(artist);
        }
    }

    private <ApiService> void addTrack(Track track) {
        ApiService apiService;
        APIClient ApiClient;
        apiService = ApiClient.getClient().create(ApiService.class);
        Call<Track> call = apiService.addTrack(track);
        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(@NonNull Call<Track> call, @NonNull Response<Track> response) {
                if (response.isSuccessful()) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(AddEditTrackActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Track> call, @NonNull Throwable t) {
                Toast.makeText(AddEditTrackActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
}
