package com.example.vrar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val webViewer: WebView = root.findViewById(R.id.webViewer)
        webViewer.settings.javaScriptEnabled = true
        webViewer.settings.allowFileAccessFromFileURLs = true
        webViewer.settings.allowFileAccess = true
        webViewer.settings.builtInZoomControls = true
        webViewer.settings.useWideViewPort = true
        webViewer.settings.loadWithOverviewMode = true
       homeViewModel.text.observe(this, Observer {

            //webViewer.loadUrl("file:///android_asset/altair/index.html")
           webViewer.loadUrl("file:///android_asset/htmls/home.html")

        })
        return root
    }
}