package com.hngkyt.vr.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.hngkyt.vr.R;
import com.hngkyt.vr.net.DownloadTask;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.DataUser;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hngkyt.vr.net.been.VersionBean;
import com.hzgktyt.vr.baselibrary.utils.AppUtils;
import com.orhanobut.logger.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wrf on 2016/11/17.
 */

public class PersonalCenterActivity extends TitleBarActivity implements RadioGroup.OnCheckedChangeListener {
    public static final int REQUEST_CODE = 1;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonAutoPlay;
    private RadioButton mRadioButtonLoopPlay;
    private Switch mSwitch;
    private TextView mTextViewUsername;
    private TextView mTextViewUpdateVersion;
    private DataUser mDataUser;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(getString(R.string.personal_center));

        mTextViewUpdateVersion = (TextView) findViewById(R.id.textview_personal_center_updata_version);
        mTextViewUpdateVersion.setOnClickListener(this);

        mSwitch = (Switch) findViewById(R.id.switch_personal_center_stereo);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSPUtils.putBoolean(String.valueOf(R.id.switch_personal_center_stereo), isChecked);
            }
        });

        mSwitch.setChecked(mSPUtils.getBoolean(String.valueOf(R.id.switch_personal_center_stereo), true));

        mTextViewUsername = (TextView) findViewById(R.id.textview_personal_center_login_signup);


        mDataUser = getUserInfo();

        if (mDataUser == null) {
            mTextViewUsername.setText(R.string.login_or_signup);
        } else {
            mTextViewUsername.setText(mDataUser.getUserName());
        }

        mTextViewUsername.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_personal_center);
        mRadioButtonAutoPlay = (RadioButton) findViewById(R.id.radiobutton_personal_center_autoplay_next);
        mRadioButtonLoopPlay = (RadioButton) findViewById(R.id.radiobutton_personal_center_loop);


        mRadioGroup.setOnCheckedChangeListener(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            tnitRadioButton(mRadioButtonAutoPlay);
            tnitRadioButton(mRadioButtonLoopPlay);
        }


        setRadioButtonsStatus(mSPUtils.getBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), true));
        //        mRadioButtonLoopPlay.setChecked();
    }

    public void setRadioButtonsStatus(boolean isLoop) {
        if (isLoop) {
            mRadioButtonLoopPlay.setChecked(true);
        } else {
            mRadioButtonAutoPlay.setChecked(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), mRadioButtonLoopPlay.isSelected());
        outState.putBoolean(String.valueOf(R.id.switch_personal_center_stereo), mSwitch.isChecked());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setRadioButtonsStatus(savedInstanceState.getBoolean(String.valueOf(R.id.radiobutton_personal_center_loop)));
        mSwitch.setChecked(savedInstanceState.getBoolean(String.valueOf(R.id.switch_personal_center_stereo)));

    }

    private void tnitRadioButton(RadioButton radioButton) {
        Drawable drawable = getResources().getDrawable(R.drawable.abc_btn_radio_material).mutate();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.colorAccent));
        radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);


    }

    @Override
    protected void onTitleBarLeftClick() {
        super.onTitleBarLeftClick();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radiobutton_personal_center_autoplay_next:

                mSPUtils.putBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), false);

                break;
            case R.id.radiobutton_personal_center_loop:

                mSPUtils.putBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), true);

                break;


        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textview_personal_center_login_signup:

                if (mDataUser != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.unregister);
                    builder.setMessage(R.string.exit_login);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveUserInfo(null);
                            mDataUser = null;
                            mTextViewUsername.setText(R.string.login_or_signup);

                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();

                } else {
                    Intent intent = new Intent(PersonalCenterActivity.this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);

                }
                break;
            case R.id.textview_personal_center_updata_version:
                checkVersion();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (REQUEST_CODE == requestCode) {
                mDataUser = data.getParcelableExtra(DataUser.class.getCanonicalName());
                mTextViewUsername.setText(mDataUser.getUserName());
            }
        }

    }

    /**
     * 检测版本
     */
    private void checkVersion() {
        Call<ResponseBean> responseBeanCall = mRequestService.getVersion();
        ResultCall<VersionBean> versionBeanResultCall = new ResultCall<>(this, VersionBean.class, false);
        versionBeanResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                VersionBean versionBean = (VersionBean) o;
                versionBean.setVesionCode(999999);//仅供测试
                if (versionBean.getVesionCode() > AppUtils.getAppVersionCode(PersonalCenterActivity.this)) {
                    initDownloadDialog(versionBean);
                }

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

            }
        });
        responseBeanCall.enqueue(versionBeanResultCall);


    }

    private void initDownloadDialog(VersionBean versionBean) {
        //用这个可以android.support.v7.appcompat.R.style.ThemeOverlay_AppCompat_Dialog_Alert可以符合当前的主题颜色
        //不然尼玛的都是默认绿色
        ProgressDialog progressDialog = new ProgressDialog(PersonalCenterActivity.this
                , android.support.v7.appcompat.R.style.ThemeOverlay_AppCompat_Dialog_Alert);
        progressDialog.setTitle(versionBean.getUpdateTitle());
        progressDialog.setMessage(versionBean.getUpdateContent());
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        final DownloadTask downloadTask = new DownloadTask(progressDialog);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.download_now), (DialogInterface.OnClickListener) null);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadTask.cancel(true);
            }
        });

        progressDialog.show();

        //这里为了点击以后不消失，选择在对话框显示以后在设置监听
        final Button positiveButton = progressDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("positiveButton");
                //"https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk" QQapk的测试地址
                Call<ResponseBody> responseBodyCall = mRequestService
                        .downloadFileWithDynamicUrlSync("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk");
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        Logger.e("response.raw()" + response.raw());
                        if (response.isSuccessful()) {
                            if (downloadTask.getStatus() == AsyncTask.Status.PENDING) {
                                downloadTask.execute(response.body());
                                positiveButton.setEnabled(false);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Logger.e("call = " + call.toString());
                        Logger.e("Throwable = " + t.getMessage());
                    }
                });
            }
        });
    }


}
