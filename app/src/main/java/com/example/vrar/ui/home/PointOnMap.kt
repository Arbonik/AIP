package com.example.vrar.ui.home

import android.graphics.*
import android.util.Log
import androidx.core.graphics.plus
import androidx.core.graphics.toRectF

class PointOnMap(centr : PointF, text: String, navigate : Int) {
    var navigate = navigate // ссылка на соответствующий фрагмент
    val rMarks = 30f // радиус маркера
    var point :PointF = centr // координаты центра маркера
    var color : Int = Color.rgb(255,69,0) //цвет маркера
    var text : String = text // текст маркера
    var active = false // активен ли маркер
    var clickOnMe = 0 // считает количество нажатий
    var maskBackground : Rect? = null // маска для перехода по нажатию на текст
    fun draw(canvas :Canvas,paint : Paint){
        paint.color = color
        canvas.drawCircle(point.x, point.y, rMarks + rMarks * 0.2f, paint)
        paint.color = Color.rgb(255,140,0)
        canvas.drawCircle(point.x, point.y, rMarks, paint)
        if (active)
        textOutput(canvas, paint)
    }

    fun textOutput(canvas: Canvas, paint: Paint){

        var what= Rect()
        maskBackground = what
        paint.getTextBounds(text,0, text.length, what)
        paint.color = Color.rgb(185,66,0)
        //what.bottom += 15 // растет вверх
        //what.top += 15 // верхняя граница ползет вниз
        //what.top -= 15 // растет вверх
        //what.bottom -= 15 // верхняя граница ползет вниз
        if (point.x < canvas.width / 2){ // если надпись находится в левой части экрана
            drawBackForText(canvas, paint, what, true)
            paint.color = Color.BLACK
            canvas.drawText(text,point.x,point.y, paint)
        }
        if (point.x > canvas.width / 2){ // если надпись находится в правой части экрана
            drawBackForText(canvas, paint, what, false)
            paint.color = Color.BLACK
            canvas.drawText(text,point.x - what.width() ,point.y, paint)
        }
    }

    fun drawBackForText(canvas: Canvas, paint: Paint, what : Rect, check : Boolean){
        // check = переменная, определяет с какой стороны экрана отрисовывается фон
        canvas.drawCircle(point.x , point.y - what.height() / 2f,
            what.height() / 2f, paint)
        if(check)
        canvas.drawCircle(point.x + what.width(),point.y - what.height() / 2f,
            what.height() / 2f, paint)
        else
            canvas.drawCircle(point.x - what.width(),point.y - what.height() / 2f,
                what.height() / 2f, paint)
        if(check)
        canvas.drawRect(point.x,point.y, point.x + what.width() , point.y - what.height(), paint)
        else
        canvas.drawRect(point.x,point.y , point.x - what.width(), point.y - what.height(), paint)
    }


    fun put(pointF: PointF):Boolean{
        if ((pointF.x <= point.x + rMarks) &&(pointF.x >= point.x - rMarks) &&
            (pointF.y <= point.y + rMarks) &&(pointF.y >= point.y - rMarks)) {
            activate()
            return true
        }
        if (clickOnMe == 1) // если нажатие повторное нужно проверить и всплывающее окно
            if ((pointF.x <= point.x + maskBackground!!.width()) &&(maskBackground!!.width() >= point.x - maskBackground!!.width()) &&
                (pointF.y <= point.y + maskBackground!!.height()) &&(pointF.y >= point.y - maskBackground!!.height())){
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
        maskBackground = null
    }

}