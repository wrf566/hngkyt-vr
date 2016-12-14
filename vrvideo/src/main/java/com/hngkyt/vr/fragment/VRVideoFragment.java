package com.hngkyt.vr.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.hngkyt.vr.R;
import com.hngkyt.vr.model.VideoItemModel;
import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * Created by wrf on 2016/12/5.
 */

public class VRVideoFragment extends BaseFragment implements View.OnClickListener {


    private static final String STATE_IS_PLAY = "isPlay";
    private static final String STATE_CURRENT_POSITION = "state_current_position";
    private static final String STATE_VIDEO_DURATION = "videoDuration";
    private static final int DISPLAYMODE_PORTRAIT = 1;
    private static final int DISPLAYMODE_LANDSCAPE = 2;
    private static final int DISPLAYMODE_STEREO = 3;
    private FrameLayout mFrameLayoutController;
    private CheckBox mCheckBoxPlay;
    private TextView mTextViewCurrentTime;
    private TextView mTextViewTotaltime;
    private ImageView mImageViewFullScreen;
    private ImageView mImageViewBack;
    private ImageView mImageViewStereo;
    private VrVideoView mVrVideoView;
    private SeekBar mSeekBar;
    private VideoItemModel mVideoItemModel;
    private boolean isCompletion = false;
    private VideoLoaderTask mVideoLoaderTask;
    private CompoundButton.OnCheckedChangeListener playChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Logger.e("isChecked = " + isChecked);

            //第一次运行
            if (mVideoLoaderTask == null) {
                if (mBaseActivity.mSPUtils.getBoolean(String.valueOf(R.id.switch_personal_center_stereo), true)) {
                    mVrVideoView.setDisplayMode(DISPLAYMODE_STEREO);
                } else {
                    mVrVideoView.setDisplayMode(DISPLAYMODE_PORTRAIT);
                }
                mVideoLoaderTask = new VideoLoaderTask();
                mVideoLoaderTask.execute(mVideoItemModel.getCoverURL());
                //第一次运行后进度条才能拖动
                mSeekBar.setEnabled(true);
                return;
            }
            //重新播放
            if (isCompletion) {
                if (isChecked) {
                    mVrVideoView.seekTo(0);
                    isCompletion = false;
                }
            } else {
                if (isChecked) {
                    mVrVideoView.playVideo();
                } else {
                    mVrVideoView.pauseVideo();
                }
            }
        }
    };
    /**
     * 只有当用户拖动进度条的时候改变播放时间
     */
    private SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mTextViewCurrentTime.setText(DateUtils.formatElapsedTime(progress / 1000));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            if (seekBar.getProgress() != seekBar.getMax()) {
                isCompletion = false;
            }
            //这里是防止播放完毕后用户再拖动进度条，如果光调用seekTo()，会直接播放
            //但是实际上，我们的按钮是可播放样式，所以要立马暂停
            //其实未播放完毕的时候调用seekTo是不会影响暂停和播放状态的
            //即拖动前是在播放或暂停那么拖动后也是播放或暂停，但是只要播放完毕了，那么调用seekTo就无视之前的状态自动播放
            //由于播放完毕了就表示视频已经暂停了，所以播放完毕的拖动全部都是暂停的，除非用户手动点击播放
            mVrVideoView.seekTo(seekBar.getProgress());
            if (!mCheckBoxPlay.isChecked()) {
                mVrVideoView.pauseVideo();
            }

        }


    };

    public static VRVideoFragment newInstance(VideoItemModel videoItemModel) {

        Bundle args = new Bundle();
        args.putParcelable(VideoItemModel.class.getCanonicalName(), videoItemModel);

        VRVideoFragment fragment = new VRVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int intLayoutResId() {
        return R.layout.fragment_vrvideo;
    }

    @Override
    protected void initView(View view) {

        mFrameLayoutController = (FrameLayout) view.findViewById(R.id.framelayout_vrvideo_controller);
        mCheckBoxPlay = (CheckBox) view.findViewById(R.id.checkbox_vrvideo_play);

        mTextViewCurrentTime = (TextView) view.findViewById(R.id.textview_vrvideo_currenttime);
        mTextViewTotaltime = (TextView) view.findViewById(R.id.textview_vrvideo_totaltime);

        mImageViewFullScreen = (ImageView) view.findViewById(R.id.imageview_vrvideo_fullscreen);
        mImageViewBack = (ImageView) view.findViewById(R.id.imageview_vrvideo_back);
        mImageViewStereo = (ImageView) view.findViewById(R.id.imageview_vrvideo_stereo);

        mImageViewFullScreen.setOnClickListener(this);
        mImageViewBack.setOnClickListener(this);
        mImageViewStereo.setOnClickListener(this);
        mCheckBoxPlay.setOnCheckedChangeListener(playChangeListener);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_vrvideo);
        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        //第一次进来还没播放的时候就不让拖动
        mSeekBar.setEnabled(false);

        mVideoItemModel = getArguments().getParcelable(VideoItemModel.class.getCanonicalName());


        mVrVideoView = (VrVideoView) view.findViewById(R.id.vrvideoview_vrvideo);
        mVrVideoView.setEventListener(new ActivityEventListener());

        mVrVideoView.setDisplayMode(DISPLAYMODE_PORTRAIT);


        hideDefaultViews(mVrVideoView);


    }

    /**
     * 隐藏默认的播放控制按钮
     *
     * @param vrVideoView VR播放组件
     */
    private void hideDefaultViews(VrVideoView vrVideoView) {

        vrVideoView.setFullscreenButtonEnabled(false);
        vrVideoView.setInfoButtonEnabled(false);
        vrVideoView.setStereoModeButtonEnabled(false);
        vrVideoView.setTransitionViewEnabled(false);
        VrWidgetView vrWidgetView = vrVideoView;

        //        vrWidgetView.


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("onActivityCreated");
        if (savedInstanceState != null) {
            mVrVideoView.seekTo(savedInstanceState.getLong(STATE_CURRENT_POSITION));
            mSeekBar.setMax((int) savedInstanceState.getLong(STATE_VIDEO_DURATION));
            mSeekBar.setProgress((int) savedInstanceState.getLong(STATE_CURRENT_POSITION));
            mCheckBoxPlay.setChecked(savedInstanceState.getBoolean(STATE_IS_PLAY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Logger.e("onSaveInstanceState");
        savedInstanceState.putLong(STATE_CURRENT_POSITION, mVrVideoView.getCurrentPosition());
        savedInstanceState.putLong(STATE_VIDEO_DURATION, mVrVideoView.getDuration());
        savedInstanceState.putBoolean(STATE_IS_PLAY, mCheckBoxPlay.isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {
        Logger.e("onResume");
        mVrVideoView.resumeRendering();

        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.e("onPause");
        mVrVideoView.pauseRendering();
        //应用被遮盖要暂停
        mCheckBoxPlay.setChecked(false);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        //        mVrVideoView.pauseRendering();//要加上不然切换出去再进来会报错
        mVrVideoView.shutdown();
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_vrvideo_fullscreen:
                mVrVideoView.setDisplayMode(2);
                break;
            case R.id.imageview_vrvideo_back:
                getActivity().onBackPressed();
                break;
            case R.id.imageview_vrvideo_stereo:
                mVrVideoView.setDisplayMode(3);
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e("onDetach");
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
            mTextViewTotaltime.setText(DateUtils.formatElapsedTime(mVrVideoView.getDuration() / 1000));


        }

        /**
         * Called by video widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
        }

        @Override
        public void onClick() {

            if (mVrVideoView.getDisplayMode() == DISPLAYMODE_PORTRAIT) {
                if (mFrameLayoutController.getVisibility() == View.VISIBLE) {
                    mFrameLayoutController.setVisibility(View.GONE);
                } else {
                    mFrameLayoutController.setVisibility(View.VISIBLE);

                }
            }


        }

        /**
         * Update the UI every frame.
         */
        @Override
        public void onNewFrame() {
            mSeekBar.setProgress((int) mVrVideoView.getCurrentPosition());
            mTextViewCurrentTime.setText(DateUtils.formatElapsedTime(mVrVideoView.getCurrentPosition() / 1000));
        }


        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
            Logger.e("onCompletion");
            if (mBaseActivity.mSPUtils.getBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), true)) {
                mVrVideoView.seekTo(0);
            } else {
                isCompletion = true;
                mCheckBoxPlay.setChecked(false);
            }


        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            super.onDisplayModeChanged(newDisplayMode);
            Logger.e("newDisplayMode = " + newDisplayMode);
            switch (newDisplayMode) {
                case DISPLAYMODE_PORTRAIT://竖屏

                    break;
                case DISPLAYMODE_LANDSCAPE://普通全屏

                    break;
                case DISPLAYMODE_STEREO://立体全屏

                    break;

            }
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
                //                options.inputFormat = VrVideoView.Options.FORMAT_HLS;
                //                                options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
                mVrVideoView.loadVideo(Uri.parse(files[0]), options);


                //                mVrVideoView.loadVideo(Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"), options);
                //                mVrVideoView.loadVideo(Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8"), options);
            } catch (IOException e) {
                e.printStackTrace();

            }

            return true;
        }
    }
}
