package com.example.vrar.ui.home

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.plus


import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController

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
                var intentPlace = img.checkOnTouch(PointF(p1.x, p1.y)) // получаем место, если пользователь нажал на кнопку 2 раз
                if (intentPlace != null)
                    navController.navigate(intentPlace.navigate) // переходим на это место
            }
            true
        }
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
        PointOnMap(PointF(532f,225f), resources.getString(R.string.menu_altair), R.id.nav_altair),
       // PointOnMap(PointF(524f,320f),resources.getString(R.string.menu_ozero), R.id.nav_altair),
        PointOnMap(PointF(1322f,314f),resources.getString(R.string.menu_nikolay), R.id.nav_nikolay),
        PointOnMap(PointF(690f,440f),resources.getString(R.string.menu_prostor), R.id.nav_prostor),
        PointOnMap(PointF(417f,540f),resources.getString(R.string.menu_villiage), R.id.nav_villiage),
        PointOnMap(PointF(479f,605f),resources.getString(R.string.menu_villiage_map), R.id.nav_villiage_map),
        PointOnMap(PointF(1023f,770f),resources.getString(R.string.menu_cavemap), R.id.nav_cave),
        PointOnMap(PointF(1234f,782f),resources.getString(R.string.menu_chudesa), R.id.nav_chudesa),
        PointOnMap(PointF(1158f,870f),resources.getString(R.string.menu_cave), R.id.nav_cc)
    )

    fun checkOnTouch(pointF: PointF):PointOnMap?{
        for (i in (0..pointsMap.size - 1)) { // проходим по маркерам
            if (pointsMap[i].put(pointF)){ // если пользователь нажал на маркер
                    if (pointsMap[i].clickOnMe == 2) // если пользователь нажал на него дважды
                        return pointsMap[i] // возвращаем чтобы перейти по хранящейся в нём ссылке
                    offAllOther(pointsMap[i]) // стираем предыдущую метку
                return null // завершаем функцию чтобы не вызвать дальнейшего затирания
            }
        }
        offAllOther(null) // если пользователь нажал мимо маркера затираем всё
        return null
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
            invalidate()
            pointsMap[i].draw(canvas, paint)
        }
    }

    fun cx (x:Float):Float{
        return (w2 * x) / w1 // трансформация координат под расширение устройства
    }
    fun cy (y:Float):Float{ // трансформация координат под расширение устройства
        return (h2 * y) / h1
    }

    fun initialize(){
        paint.textSize = 60f
        for (i in pointsMap){
            i.point = PointF(cx(i.point.x), cy(i.point.y))
        }
    }

    fun offAllOther(pon : PointOnMap?){
        for (i in pointsMap){
            if (i == pon){}
            else
            i.disAcivated()
        }
    }

    inner class PointOnMap(centr : PointF, text: String, navigate : Int) {
        var navigate = navigate // ссылка на соответствующий фрагмент
        val rMarks = 30f // радиус маркера
        var point :PointF = centr // координаты центра маркера
        var color : Int = resources.getColor(R.color.colorPrimaryDark) //цвет обводки маркера
        var text : String = text // текст маркера
        var active = false // активен ли маркер
        var clickOnMe = 0 // считает количество нажатий
        var maskBackground : Rect? = null // маска для перехода по нажатию на текст
        fun draw(canvas :Canvas,paint : Paint){
            paint.color = color
            canvas.drawCircle(point.x, point.y, rMarks + rMarks * 0.2f, paint)
            paint.color =  resources.getColor(R.color.colorPrimary)
            canvas.drawCircle(point.x, point.y, rMarks, paint)
            if (active)
                textOutput(canvas, paint)
        }

        fun textOutput(canvas: Canvas, paint: Paint){
            var what= Rect()
            maskBackground = what
            paint.getTextBounds(text,0, text.length, what)
            if (point.x < canvas.width / 2){ // если надпись находится в левой части экрана
                paint.color = resources.getColor(R.color.colorPrimaryDark)
                drawBackForText(canvas, paint, Rect(what + Rect(-5,-10,10,20)) , true)
                paint.color = resources.getColor(R.color.colorPrimary)
                drawBackForText(canvas, paint, what, true)
                paint.color = Color.WHITE
                canvas.drawText(text,point.x,point.y + what.height() / 2f - 12f, paint)
            }
            if (point.x > canvas.width / 2){ // если надпись находится в правой части экрана
                paint.color = resources.getColor(R.color.colorPrimaryDark)
                drawBackForText(canvas, paint, Rect(what + Rect(-5,-10,10,20)), false)
                paint.color = resources.getColor(R.color.colorPrimary)
                drawBackForText(canvas, paint, what, false)
                paint.color = Color.WHITE
                canvas.drawText(text,point.x - what.width() ,point.y + what.height() / 2f - 12f, paint)
            }
        }

        fun drawBackForText(canvas: Canvas, paint: Paint, what : Rect, check : Boolean){
            // check = переменная, определяет с какой стороны экрана отрисовывается фон
            canvas.drawCircle(point.x , point.y,
                what.height() / 2f, paint)
            if(check)
                canvas.drawCircle(point.x + what.width(),point.y ,
                    what.height() / 2f, paint)
            else
                canvas.drawCircle(point.x - what.width(),point.y ,
                    what.height() / 2f, paint)
            if(check)
                canvas.drawRect(point.x,point.y + what.height() / 2f, point.x + what.width() , point.y - what.height()  / 2f, paint)
            else
                canvas.drawRect(point.x,point.y + what.height() / 2f, point.x - what.width(), point.y - what.height()  / 2f, paint)
        }


        fun put(pointF: PointF):Boolean{
            if ((pointF.x <= point.x + rMarks) &&(pointF.x >= point.x - rMarks) &&
                (pointF.y <= point.y + rMarks) &&(pointF.y >= point.y - rMarks)) {
                activate()
                return true
            }
            if (clickOnMe == 1) // если нажатие повторное нужно проверить и всплывающее окно
            {
                if(point.x < this@MView.width / 2)
                    if ((pointF.x <= point.x + maskBackground!!.width()) && (pointF.x >= point.x ) &&
                        (pointF.y <= point.y + maskBackground!!.height() / 2f ) && (pointF.y >= point.y - maskBackground!!.height()) )
                    {
                        activate()
                        return true
                    }
                if(point.x > this@MView.width / 2)
                    if ((pointF.x <= point.x) && (pointF.x >= point.x  - maskBackground!!.width()) &&
                        (pointF.y <= point.y + maskBackground!!.height() / 2f ) && (pointF.y >= point.y - maskBackground!!.height()) )
                    {
                        activate()
                        return true
                    }
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
}
