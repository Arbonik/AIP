package com.example.vrar.ui.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF

class PointOnMap(centr : PointF, text: String, color : Int) {
    val rMarks = 30f
    var point :PointF = centr
    var color : Int = color
    var text : String = text

    fun draw(canvas :Canvas,paint : Paint){
        paint.color = color
        canvas.drawCircle(point.x, point.y, rMarks, paint)
        paint.color = Color.BLACK
        canvas.drawText(text,point.x + rMarks,point.y + rMarks / 2, paint)
    }
    fun put(pointF: PointF){

        if ((pointF.x <= point.x + rMarks) &&(pointF.x >= point.x + rMarks) &&
            (pointF.y <= point.y + rMarks) &&(pointF.y >= point.y + rMarks)) {
            activate()
        }
    }
    fun activate(){
        text = "POOOW"
    }
}