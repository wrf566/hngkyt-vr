package com.hzgktyt.vr.baselibrary.utils;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by wrf on 2016/12/19.
 */

public class EditTextUtils {

    public static boolean isInputNull(EditText editText,int stringId){
        if(TextUtils.isEmpty(editText.getText().toString().trim())){
            return true;
        }else{
            ToastUtils.showShortToast(editText.getContext(),stringId);
            return false;
        }
    }
}
