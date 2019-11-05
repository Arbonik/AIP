package com.example.vrar.ui.altayir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class ZeroKilometrFragment : Fragment() { // altair

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_altair, container, false)

        var webView : WebView = root.findViewById(R.id.altair_show)

        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccessFromFileURLs = true

           webView.loadUrl("file:///android_asset/altair/index.html")
        return root
    }
}