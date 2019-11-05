package com.example.vrar.ui.dtqvant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class ChudesaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_share, container, false)

        val webViewer: WebView = root.findViewById(R.id.share_web)
        webViewer.settings.javaScriptEnabled = true
        webViewer.settings.allowFileAccessFromFileURLs = true


            webViewer.loadUrl("http://дткванториум.рф/barnaul/Hackaton")

        return root
    }
}