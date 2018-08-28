package com.abiramiaudio.apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeFailureRecoveryActivity {

    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    String id;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.youtube_player);
        playerView = (YouTubePlayerView) findViewById(R.id.player);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("B71D897C6FB5FFEC8184442E74C7E952").build());


        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("B71D897C6FB5FFEC8184442E74C7E952").build());
            }

        });

        // You can use your own button to switch to fullscreen too
        Intent intent = getIntent();
        id = intent.getStringExtra("key");

        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);


    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        this.player = youTubePlayer;
        player.setPlayerStateChangeListener(mPlayerStateChangeListener);
        // Specify that we want to handle fullscreen behavior ourselves.
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        if (!wasRestored) {
            player.loadVideo(id);
        }
    }

    YouTubePlayer.PlayerStateChangeListener mPlayerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {
            player.play();
        }

        @Override
        public void onAdStarted() {
            player.pause();
        }

        @Override
        public void onVideoStarted() {

            interstitialAd.show();

        }

        @Override
        public void onVideoEnded() {

            //interstitialAd.show();
            startActivity(new Intent(PlayerActivity.this,MainActivity.class));
            interstitialAd.show();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

}
