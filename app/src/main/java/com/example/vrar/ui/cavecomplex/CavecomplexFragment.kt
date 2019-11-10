package com.example.vrar.ui.cavecomplex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class CavecomplexFragment : Fragment() { // altair


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_cavecomplex, container, false)

        var webView : WebView = root.findViewById(R.id.webViewer)

        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.allowFileAccess = true
        webView.settings.builtInZoomControls = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true

        webView.loadUrl("file:///android_asset/htmls/cave_complex.html")
        return root
    }
}