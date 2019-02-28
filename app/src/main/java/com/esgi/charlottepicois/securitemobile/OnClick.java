package com.esgi.charlottepicois.securitemobile;

import android.support.annotation.IdRes;
import android.view.View;

@interface OnClick {
    @IdRes int[] value() default { View.NO_ID };
}
