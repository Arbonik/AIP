package com.example.vrar.ui.home

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vrar.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val img :MView = root.findViewById(R.id.map)

//        val webViewer: WebView = root.findViewById(R.id.webViewer)
//        webViewer.settings.javaScriptEnabled = true
//        webViewer.settings.allowFileAccessFromFileURLs = true
//        webViewer.settings.allowFileAccess = true
//
//        webViewer.settings.useWideViewPort = true
//
      //  webViewer.settings.loadWithOverviewMode = true
        // webViewer.settings.builtInZoomControls = false

        homeViewModel.text.observe(this, Observer {
         //  webViewer.loadUrl("file:///android_asset/htmls/home.html")

        })
        return root
    }
}

class MView(context: Context, attributeSet: AttributeSet) : ImageView(context, attributeSet){
    val h1 = 967f
    val w1 = 1582f // оригинальные размеры карты
    var h2 = 0f
    var w2 = 0f // размеры холста
    var suka = true
    val rMarks = 30f
    var paint = Paint()
    var pointsMap : Array<PointOnMap> = arrayOf(
        PointOnMap(PointF(522f,225f),"Нулевой километр", Color.YELLOW),
        PointOnMap(PointF(524f,320f),"Нулевой километр", Color.MAGENTA),
        PointOnMap(PointF(1322f,314f),"Нулевой километр", Color.RED)

    )


    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        if (suka){
            h2 = canvas.height.toFloat()
            w2 = canvas.width.toFloat()
            initialize()
            suka = !suka
        }

        for (i in (0..pointsMap.size - 1)){

            paint.color = pointsMap[i].color
            paint.textSize = 30f
            canvas.drawCircle(pointsMap[i].point.x, pointsMap[i].point.y, rMarks, paint)
            canvas.drawText(pointsMap[i].text, pointsMap[i].point.x, pointsMap[i].point.y, paint)
            invalidate()
        }
    }

    fun cx (x:Float):Float{
        return (w2 * x) / w1
    }
    fun cy (y:Float):Float{
        return (h2 * y) / h1
    }

    fun initialize(){
        for (i in pointsMap){
            i.point = PointF(cx(i.point.x), cy(i.point.y))
        }
    }
}
