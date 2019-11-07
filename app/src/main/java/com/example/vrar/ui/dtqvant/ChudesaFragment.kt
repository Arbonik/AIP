package com.example.vrar.ui.dtqvant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.vrar.R

class ChudesaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = Navigation.findNavController(container!!.findViewById(R.id.nav_host_fragment))
        val root = inflater.inflate(R.layout.fragment_shudesa, container, false)

        val webViewer: WebView = root.findViewById(R.id.share_web)
        webViewer.settings.javaScriptEnabled = true
        webViewer.settings.allowFileAccessFromFileURLs = true


            webViewer.loadUrl("http://дткванториум.рф/barnaul/Hackaton")
        navController.navigate(R.id.nav_home)
        return root
    }
}