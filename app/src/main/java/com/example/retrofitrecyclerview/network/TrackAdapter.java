package com.example.retrofitrecyclerview.network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitrecyclerview.R;
import com.example.retrofitrecyclerview.model.Track;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private List<Track> trackList;
    private Context context;
    private OnTrackClickListener listener;

    public TrackAdapter(List<Track> trackList, Context context, OnTrackClickListener listener) {
        this.trackList = trackList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = trackList.get(position);
        holder.titleTextView.setText(track.getTitle());
        holder.artistTextView.setText(track.getArtist());

        holder.itemView.setOnClickListener(v -> listener.onTrackClick(track));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public interface OnTrackClickListener {
        void onTrackClick(Track track);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView artistTextView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.trackTitle);
            artistTextView = itemView.findViewById(R.id.trackArtist);
        }
    }
}

