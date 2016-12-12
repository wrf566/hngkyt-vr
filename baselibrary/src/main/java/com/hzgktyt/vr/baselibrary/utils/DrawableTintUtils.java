package com.hzgktyt.vr.baselibrary.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by wrf on 2016/12/12.
 */

public class DrawableTintUtils {

    public static void tintDrawable(Drawable drawable, int colorInt) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable.mutate(), colorInt);
    }

    public static Drawable getTintDrawable(Drawable drawable, int colorInt) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable.mutate(), colorInt);

        return drawable;
    }


    public static StateListDrawable selectStateListDrawable(Drawable normaldrawable, Drawable selecteddrawable) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_selected},
                selecteddrawable);
        states.addState(new int[]{},
                normaldrawable);

        return states;
    }
}
