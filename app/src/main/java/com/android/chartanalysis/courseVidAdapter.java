package com.android.chartanalysis;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class courseVidAdapter extends RecyclerView.Adapter<courseVideoViewHolder> {

    ArrayList<videoData> data;
    private Context context;


    public courseVidAdapter(ArrayList<videoData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public courseVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video,parent,false);
        return new courseVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull courseVideoViewHolder holder, int position) {

        videoData videoData;
        videoData = data.get(position);

        try{
//            String link = videoData.getUrl();
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.video);
//            Uri video = Uri.parse(link);
//            holder.video.setMediaController(mediaController);
//            holder.video.setVideoURI(video);
//            holder.video.start();

            holder.video.setVideoURI(Uri.parse(videoData.getUrl()));
            holder.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    holder.vid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mediaPlayer.setLooping(true);
                            mediaPlayer.start();

                        }
                    });

                }
            });


        }
        catch (Exception e){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }


        holder.titel.setText(videoData.getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void upDate(ArrayList<videoData>upDate){
        data.clear();
        data.addAll(upDate);

        notifyDataSetChanged();
    }
}

class courseVideoViewHolder extends RecyclerView.ViewHolder{

    VideoView video;
    TextView titel;
    Context context;
    ConstraintLayout vid;

    public courseVideoViewHolder(@NonNull View itemView) {
        super(itemView);
        video = itemView.findViewById(R.id.videoView1);
        titel = itemView.findViewById(R.id.title1);
        vid = itemView.findViewById(R.id.vid);

    }
}