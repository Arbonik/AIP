package com.example.vrar.ui.cavemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cave, container, false)

        var web: WebView = root.findViewById(R.id.cave_show)

        web.settings.javaScriptEnabled = true
        web.settings.allowFileAccessFromFileURLs = true
        web.settings.allowFileAccess = true
        web.settings.builtInZoomControls = true
        web.settings.useWideViewPort = true
        web.settings.loadWithOverviewMode = true

        toolsViewModel.text.observe(this, Observer {
            web.loadUrl("file:///android_asset/htmls/cavemap.html")
        })
        return root
    }
}