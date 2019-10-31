package com.example.vrar.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class SlideshowFragment : Fragment() { // altair

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_altair, container, false)

        var webView : WebView = root.findViewById(R.id.altair_show)

        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccessFromFileURLs = true
//        webView.settings.allowFileAccess = true
//        webView.settings.builtInZoomControls = true
//        webView.settings.useWideViewPort = true
//        webView.settings.loadWithOverviewMode = true
        slideshowViewModel.text.observe(this, Observer {
           webView.loadUrl("file:///android_asset/altair/index.html")
        })
        return root
    }
}