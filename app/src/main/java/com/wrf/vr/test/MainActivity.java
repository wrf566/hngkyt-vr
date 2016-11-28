package com.wrf.vr.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private VrVideoView mVrVideoView;

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVrVideoView = (VrVideoView) findViewById(R.id.vrvodeoview);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);


        mSeekBar.setOnSeekBarChangeListener(this);
        mVrVideoView.setEventListener(new VrVideoEventListener());
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
}
