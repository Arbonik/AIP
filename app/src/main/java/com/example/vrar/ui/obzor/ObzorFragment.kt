package com.example.vrar.ui.obzor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.vrar.R

class ProstorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = Navigation.findNavController(container!!.findViewById(R.id.nav_host_fragment))
        val root = inflater.inflate(R.layout.fragment_prostor, container, false)

        var web: WebView = root.findViewById(R.id.villiage_show)
        web.settings.javaScriptEnabled = true
        web.settings.allowFileAccessFromFileURLs = true
        web.settings.allowFileAccess = true

        //web.settings.builtInZoomControls = true

        web.settings.useWideViewPort = true
        web.settings.loadWithOverviewMode = true

        web.loadUrl("https://www.youtube.com/watch?v=xlwVKlpZcyM")
        navController.navigate(R.id.nav_home)
        return root
    }
}