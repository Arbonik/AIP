package com.example.vrar.ui.viliage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.vrar.R

class VilliageMapFragment : Fragment() { // altair

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_villiage_map, container, false)
        var vm = Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.kosmosnimki.ru/api/index.html?permalink=KWNRW"))
        val navController = Navigation.findNavController(container!!.findViewById(R.id.nav_host_fragment))
        startActivity(vm)
        navController.navigate(R.id.nav_home)
        return root
    }
}