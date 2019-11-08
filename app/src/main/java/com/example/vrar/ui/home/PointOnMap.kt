package com.example.vrar.ui.home

import android.graphics.*
import android.util.Log
import androidx.core.graphics.toRectF

class PointOnMap(centr : PointF, text: String, navigate : Int) {
    var navigate = navigate
    val rMarks = 30f
    var point :PointF = centr
    var color : Int = Color.rgb((100..255).random(), (100..255).random(),(100..255).random())
    var text : String = text
    var active = false
    var clickOnMe = 0
    fun draw(canvas :Canvas,paint : Paint){
        paint.color = color
        canvas.drawCircle(point.x, point.y, rMarks, paint)
        if (active)
        textOutput(canvas, paint)
    }

    fun textOutput(canvas: Canvas, paint: Paint){

        var what= Rect()
        paint.getTextBounds(text,0, text.length, what)
        paint.color = Color.rgb(185,66,0)
        if (point.x < canvas.width / 2){ // если надпись находится в левой части экрана

            canvas.drawRect(point.x,point.y + 15f, point.x + what.width() , point.y - what.height(), paint)
            paint.color = Color.BLACK
            canvas.drawText(text,point.x,point.y, paint)
        }
        if (point.x > canvas.width / 2){ // если надпись находится в правой части экрана
            canvas.drawRect(point.x,point.y + 15f, point.x - what.width(), point.y - what.height(), paint)
            paint.color = Color.BLACK
            canvas.drawText(text,point.x - what.width() ,point.y, paint)
        }


    }

    fun put(pointF: PointF):Boolean{
        if ((pointF.x <= point.x + rMarks) &&(pointF.x >= point.x - rMarks) &&
            (pointF.y <= point.y + rMarks) &&(pointF.y >= point.y - rMarks)) {
            activate()
            return true
        }
        return false
    }

    fun activate(){
        active = !active
        ++clickOnMe
    }

    fun disAcivated(){
        active = false
        clickOnMe = 0
    }

}