package com.example.vrar.ui.rerich

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rerich, container, false)
        val webV: WebView = root.findViewById(R.id.rerich_show)
        webV.settings.javaScriptEnabled = true
        webV.settings.allowFileAccessFromFileURLs = true
        webV.settings.allowFileAccess = true
        webV.settings.builtInZoomControls = true
        webV.settings.useWideViewPort = true
        webV.settings.loadWithOverviewMode = true

        galleryViewModel.text.observe(this, Observer {
            webV.loadUrl("file:///android_asset/htmls/rerich.html")
        })
        return root
    }
}