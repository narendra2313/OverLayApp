package com.si.overlayapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.SCROLLBARS_INSIDE_OVERLAY
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.webkit.*
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.si.overlayapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val loader by lazy { LoadingDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)

        loader.show(true)

        /*setDecorViewFullScreen {

        }*/

        binding.videoView.apply {
            val vidUri = Uri.parse("android.resource://com.si.overlayapp/raw/video")
            setVideoURI(vidUri)
            //requestFocus()
            start()
            setOnInfoListener(object : MediaPlayer.OnInfoListener {
                override fun onInfo(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
                    if (isPlaying) {
                        loader.show(false)
                    }
                    return true
                }
            })
        }
        binding.videoView.setOnClickListener {
            binding.apply {
                popUpDialogBottom.isVisible = false
                binding.clRootPopUpDialog.isVisible = false
                binding.webViewContainer.isVisible = false
            }
        }

        binding.ivSettings.apply {
            setOnClickListener {
                //binding.clRootPopUpDialog.isVisible = true
                //showLayout(true)
                if (!binding.popUpDialogBottom.isVisible) {
                    //Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()
                    binding.popUpDialogBottom.isVisible = true
                    binding.webViewContainer.isVisible = true
                }
                /*else{
                    binding.apply {
                        popUpDialogBottom.isVisible = false
                        binding.clRootPopUpDialog.isVisible = false
                        binding.rvListDialog.isVisible = false
                    }
                }*/
            }
        }
        binding.ivClose.apply {
            setOnClickListener {
                binding.clRootPopUpDialog.isVisible = false
            }
        }
        binding.tvScore.setOnClickListener {

        }
        webViewSettings()
        setWebViewClient()
        addTopTab()
    }

    private fun addTopTab() {
        binding.tlScore.removeAllTabs()

        //val tabListTitle = listOf<String>("VOTE PLAYER", "WHAT WE DO", "OUR CLIENTS")
        val tabListTitle = listOf<String>("VOTE PLAYER", "Play By Play", "Key Events")
        val urlsList = listOf<String>(
            "https://gamingdemo-poll-admin.sportz.io/#/polls?eventId=144&pollId=321&transBg=1",
            "https://www.sportzinteractive.net/what-we-do",
            "https://www.sportzinteractive.net/our-clients"
        )

        /*val urlsList = listOf<String>("https://gamingdemo-poll-admin.sportz.io/#/polls?eventId=144&pollId=321&transBg=1",
        "https://gamingdemo-poll-admin.sportz.io/#/polls?eventId=144&pollId=321&transBg=1",
            "https://gamingdemo-poll-admin.sportz.io/#/polls?eventId=144&pollId=321&transBg=1")*/


        binding.tlScore.apply {
            tabListTitle.forEachIndexed { index, category ->
                val tab = newTab().apply {
                    text = category.uppercase()
                }
                addTab(tab)
            }
        }
        /*binding.customWebView.loadData("<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"transparent\" style=\"background-color:transparent;\">\n" +
                "</body>\n" +
                "</html> ",null,null)
        */

        binding.customWebView.loadUrl(urlsList.first())


        binding.tlScore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.customWebView.post {
                    binding.customWebView.loadUrl(urlsList.get(tab?.position ?: 0))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun showLayout(show: Boolean) {
        if (show) {
            binding.clRootPopUpDialog.apply {
                isVisible = true
                animate()
                    .translationX(0F)
                    .setDuration(200)
                    .start()
            }
        } else {
            binding.clRootPopUpDialog.apply {
                animate()
                    .translationX(width.toFloat())
                    .setDuration(200)
                    .start()
            }

        }
    }

    override fun getLayoutId() = R.layout.activity_main

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSettings() {
        binding.customWebView.settings.javaScriptEnabled = true
        binding.customWebView.settings.domStorageEnabled = true
        binding.customWebView.settings.setAppCacheEnabled(false)
        binding.customWebView.isClickable = true
        binding.customWebView.webChromeClient = WebChromeClient()
        binding.customWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        binding.customWebView.scrollBarStyle = SCROLLBARS_INSIDE_OVERLAY
        //binding.customWebView.setBackgroundColor(Color.argb(1, 255, 255, 255))
        //binding.customWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        //binding.customWebView.layoutMode = MODE_NO_LOCALIZED_COLLATORS
        binding.customWebView.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }


    }


    private fun setWebViewClient() {
        binding.customWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                //loader.show(true)

                super.onPageStarted(view, url, favicon)

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                //loader.show(false)

                super.onPageFinished(view, url)
            }
        }
    }
}