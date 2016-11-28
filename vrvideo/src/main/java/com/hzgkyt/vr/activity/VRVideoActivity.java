package com.hzgkyt.vr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.hzgkyt.vr.R;

/**
 * Created by wrf on 2016/11/16.
 */

public class VRVideoActivity extends TitleBarActivity implements SeekBar.OnSeekBarChangeListener {

    private VrVideoView mVrVideoView;

    private SeekBar mSeekBar;


    @Override
    protected   int intLayoutResId() {
        return R.layout.activity_vrvideo;
    }

    @Override
  protected   void initView() {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVrVideoView = (VrVideoView) findViewById(R.id.vr_video_view);
        mVrVideoView.setEventListener(new ActivityEventListener());


        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    protected void onTitleBarLeftClick() {
        super.onTitleBarLeftClick();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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
        }

        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
        }
    }
}
