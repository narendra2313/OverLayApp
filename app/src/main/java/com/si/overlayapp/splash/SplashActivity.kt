package com.si.overlayapp.splash

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.lifecycle.lifecycleScope
import com.si.overlayapp.BaseActivity
import com.si.overlayapp.MainActivity
import com.si.overlayapp.R
import com.si.overlayapp.VideoListingActivity
import com.si.overlayapp.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        //window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(100)
            startActivity(Intent(this@SplashActivity, VideoListingActivity::class.java))
            finish()
        }
    }


    override fun getLayoutId() = R.layout.activity_splash

}