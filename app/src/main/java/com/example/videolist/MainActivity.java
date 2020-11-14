package com.example.videolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    PlayerView playerView ;
    SimpleExoPlayer player ;
    boolean playWhenReady = true ;
    int currentWindow = 0 ;
    long playbackPosition = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView = findViewById(R.id.video_view);




    }


    @Override
    protected void onStart() {
        super.onStart();
        if(Util.SDK_INT >=24) {
            initializePlayer(this);
       }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Util.SDK_INT >=24) {
            initializePlayer(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Util.SDK_INT >= 24) {
            releasePlayer() ;

        }

    }




    @Override
    protected void onStop() {
        super.onStop();
        if(Util.SDK_INT >= 24) {
            releasePlayer() ;

        }
    }


    private void initializePlayer(Context context) {
        player  =new SimpleExoPlayer.Builder(context).build() ;
        playerView.setPlayer(player);

/*
        switch() {
            case 1:

            break ;

        }
*/
        Uri url1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid1);
        Uri url2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid2);
        Uri url3 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid3);
        Uri url4 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid4);

        MediaItem item  = MediaItem.fromUri(url1);
        MediaItem item2 = MediaItem.fromUri(url2);
        MediaItem item3 = MediaItem.fromUri(url3);
        MediaItem item4 = MediaItem.fromUri(url4);

        player.addMediaItem(0,item);
        player.addMediaItem(1,item2);
        player.addMediaItem(2,item3);
        player.addMediaItem(3,item4);


        // on Resume
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow,playbackPosition);
        player.prepare() ;
        player.play();

    }

    private void releasePlayer() {
        if(player != null){
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition() ;
            currentWindow = player.getCurrentWindowIndex() ;
            player.release();
            player = null ;
        }
    }

}