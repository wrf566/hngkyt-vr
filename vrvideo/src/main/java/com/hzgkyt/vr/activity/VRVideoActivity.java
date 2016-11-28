package com.hzgkyt.vr.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.hzgkyt.vr.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by wrf on 2016/11/16.
 */

public class VRVideoActivity extends TitleBarActivity  {

    private VrVideoView mVrVideoView;

    private SeekBar mSeekBar;

    private VrVideoView.Options videoOptions = new VrVideoView.Options();


    @Override
    protected int intLayoutResId() {
        return R.layout.activity_vrvideo;
    }

    @Override
    protected void initView() {
        mVrVideoView = (VrVideoView) findViewById(R.id.vrvideoview_vrvideo);
        mVrVideoView.setEventListener(new ActivityEventListener());



        new VideoLoaderTask().execute("file:///sdcard/vrtest.mp4");

        mSeekBar = (SeekBar) findViewById(R.id.seekbar_vrvideo);
        mSeekBar.setOnSeekBarChangeListener(new SeekBarListener());
    }


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



        }

    @Override
    protected void onPause() {
        super.onPause();
        mVrVideoView.pauseRendering();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVrVideoView.shutdown();

    }

    @Override
    protected void onTitleBarLeftClick() {
        super.onTitleBarLeftClick();

    }

    /**
     * When the user manipulates the seek bar, update the video position.
     */
    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mVrVideoView.seekTo(progress);
            } // else this was from the ActivityEventHandler.onNewFrame()'s seekBar.setProgress update.
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    }


    /**
     * Listen to the important events from widget.
     */
    private class ActivityEventListener extends VrVideoEventListener {
        /**
         * Called by video widget on the UI thread when it's done loading the video.
         */
        @Override
        public void onLoadSuccess() {
            mSeekBar.setMax((int) mVrVideoView.getDuration());

        }

        /**
         * Called by video widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
        }

        @Override
        public void onClick() {
        }

        /**
         * Update the UI every frame.
         */
        @Override
        public void onNewFrame() {
            mSeekBar.setProgress((int) mVrVideoView.getCurrentPosition());

        }

        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
        }
    }


    /**
     * Helper class to manage threading.
     */
    class VideoLoaderTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... files) {
            try {

                VrVideoView.Options options = new VrVideoView.Options();
                mVrVideoView.loadVideo(Uri.parse(files[0]), options);
            } catch (IOException e) {
                e.printStackTrace();

            }

            return true;
        }
    }


//    private void togglePause() {
//
//        mVrVideoView.
//
//        if (isPaused) {
//            mVrVideoView.playVideo();
//        } else {
//            mVrVideoView.pauseVideo();
//        }
//        isPaused = !isPaused;
//        updateStatusText();
//    }
}
