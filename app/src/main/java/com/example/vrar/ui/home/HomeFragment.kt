package com.example.vrar.ui.home

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
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

        img.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (p1?.action == MotionEvent.ACTION_DOWN) {
                    img.checkOnTouch(PointF(p1.x, p1.y))
                    Log.d("COORDS", "${p1.x}, ${p1.y}")
                }
                return true
            }
        })
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

class MView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){
    val h1 = 967f
    val w1 = 1582f // оригинальные размеры карты
    var h2 = 0f
    var w2 = 0f // размеры холста
    var first = true
    var paint = Paint()
    var pointsMap : Array<PointOnMap> = arrayOf(
        PointOnMap(PointF(532f,225f),"Нулевой километр", Color.YELLOW),
        PointOnMap(PointF(524f,320f),"Нулевой километр", Color.MAGENTA),
        PointOnMap(PointF(1322f,314f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(690f,440f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(739f,493f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(417f,540f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(479f,585f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(1023f,770f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(1234f,782f),"Нулевой километр", Color.RED),
        PointOnMap(PointF(1158f,870f),"Нулевой километр", Color.RED)
    )

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun checkOnTouch(pointF: PointF){
    for (i in pointsMap)
        i.put(pointF)
    }

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        if (first){
            h2 = canvas.height.toFloat()
            w2 = canvas.width.toFloat()
            initialize()
            first = !first
        }

        for (i in (0..pointsMap.size - 1)){
            paint.color = pointsMap[i].color
            paint.textSize = 60f
            invalidate()
            pointsMap[i].draw(canvas, paint)
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
