package com.example.vrar.ui.viliage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewModel =
            ViewModelProviders.of(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_villiage, container, false)

        var web: WebView = root.findViewById(R.id.villiage_show)
        web.settings.javaScriptEnabled = true
        web.settings.allowFileAccessFromFileURLs = true
        web.settings.allowFileAccess = true
        //web.settings.builtInZoomControls = true
        web.settings.useWideViewPort = true
        web.settings.loadWithOverviewMode = true

        sendViewModel.text.observe(this, Observer {
            web.loadUrl("https://www.youtube.com/watch?v=aPIvdaRu650")
        })
        return root
    }
}