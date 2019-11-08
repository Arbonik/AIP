package com.example.vrar.ui.viliage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.vrar.R

class VilliageMapFragment : Fragment() { // altair

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_villiage_map, container, false)

        var webView : WebView = root.findViewById(R.id.villiage_map_show)

        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccessFromFileURLs = true

        webView.loadUrl("http://maps.kosmosnimki.ru/api/index.html?permalink=KWNRW")
        return root
    }
}