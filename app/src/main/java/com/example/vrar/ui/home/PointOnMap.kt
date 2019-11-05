package com.example.vrar.ui.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.Log

class PointOnMap(centr : PointF, text: String, color : Int) {
    val rMarks = 30f
    var point :PointF = centr
    var color : Int = color
    var text : String = text
    var active = false

    fun draw(canvas :Canvas,paint : Paint){
        paint.color = color
        canvas.drawCircle(point.x, point.y, rMarks, paint)
        paint.color = Color.BLACK
        if (active)
        canvas.drawText(text,point.x + rMarks,point.y + rMarks / 2, paint)
    }
    fun put(pointF: PointF){
        if ((pointF.x <= point.x + rMarks) &&(pointF.x >= point.x - rMarks) &&
            (pointF.y <= point.y + rMarks) &&(pointF.y >= point.y - rMarks)) {
            activate()
        }
    }
    fun activate(){
       active = !active
    }
}