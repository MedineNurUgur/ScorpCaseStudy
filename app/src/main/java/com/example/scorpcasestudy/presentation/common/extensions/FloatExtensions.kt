package com.example.scorpcasestudy.presentation.common.extensions

import android.content.Context

fun Float.pxFromDp(context: Context): Float {
    return this * context.getResources().getDisplayMetrics().density
}