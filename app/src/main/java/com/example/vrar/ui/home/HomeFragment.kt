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
import androidx.navigation.findNavController


import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.vrar.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val img :MView = root.findViewById(R.id.map)
        val navController = findNavController(container!!.findViewById(R.id.nav_host_fragment))

        img.setOnTouchListener { p0, p1 ->
            if (p1?.action == MotionEvent.ACTION_DOWN) {
                img.checkOnTouch(PointF(p1.x, p1.y))
                Log.d("COORDS", "${p1.x}, ${p1.y}")
            }
            true
        }

//        val webViewer: WebView = root.findViewById(R.id.webViewer)
//        webViewer.settings.javaScriptEnabled = true
//        webViewer.settings.allowFileAccessFromFileURLs = true
//        webViewer.settings.allowFileAccess = true
//
//        webViewer.settings.useWideViewPort = true
//
      //  webViewer.settings.loadWithOverviewMode = true
        // webViewer.settings.builtInZoomControls = false
       // val navController = root.findNavController()
       // navController.navigate(R.id.nav_cc)

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
        PointOnMap(PointF(532f,225f), resources.getString(R.string.menu_altair), R.id.nav_slideshow),
        PointOnMap(PointF(524f,320f),resources.getString(R.string.menu_ozero), R.id.nav_slideshow),
        PointOnMap(PointF(1322f,314f),resources.getString(R.string.menu_nikolay), R.id.nav_gallery),
        PointOnMap(PointF(690f,440f),resources.getString(R.string.menu_prostor), R.id.nav_slideshow),
//        PointOnMap(PointF(739f,493f),"Нулевой километр", randomColor()),
        PointOnMap(PointF(417f,540f),resources.getString(R.string.menu_villiage), R.id.nav_send),
        PointOnMap(PointF(479f,585f),resources.getString(R.string.menu_villiage), R.id.nav_send),
        PointOnMap(PointF(1023f,770f),resources.getString(R.string.menu_cavemap), R.id.nav_tools),
        PointOnMap(PointF(1234f,782f),resources.getString(R.string.menu_chudesa), R.id.nav_share),
        PointOnMap(PointF(1158f,870f),resources.getString(R.string.menu_cave), R.id.nav_cc)
    )

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun checkOnTouch(pointF: PointF):Boolean{
        offActive()
    for (i in (0..pointsMap.size - 1)) {
        if(pointsMap[i].put(pointF))
            return true
    }
        return false
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

    fun offActive(){
        for (i in pointsMap){
            if (i.active) {
                i.active = false
            break
            }
        }
    }
}
