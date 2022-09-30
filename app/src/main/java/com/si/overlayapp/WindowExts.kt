package com.si.overlayapp

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun Activity.setDecorViewFullScreen(onApplyWindowInsets: (inset: Insets) -> Unit) {
    window.requestFeature(Window.FEATURE_NO_TITLE)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // New API instead of SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN and SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.setDecorFitsSystemWindows(true)
        // New API instead of SYSTEM_UI_FLAG_IMMERSIVE
        window.decorView.windowInsetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE


    } else {

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN
    }
    //setOnApplyWindowInsets(onApplyWindowInsets)
}

fun Activity.setOnApplyWindowInsets(onApplyWindowInsets: (inset: Insets) -> Unit) {
    var isInsetSent = false
    ViewCompat.setOnApplyWindowInsetsListener(
        window.decorView
    ) { _, insets ->
        val systemBarsInset = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        if (!isInsetSent) {
            onApplyWindowInsets.invoke(systemBarsInset)
            isInsetSent = true
        }
        insets
    }

}

fun View.setOnApplyWindowInsets(onApplyWindowInsets: (inset: Insets) -> Unit) {
    var isInsetSent = false
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        val systemBarsInset = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        if (!isInsetSent) {
            onApplyWindowInsets.invoke(systemBarsInset)
            isInsetSent = true
        }
        insets
    }
}