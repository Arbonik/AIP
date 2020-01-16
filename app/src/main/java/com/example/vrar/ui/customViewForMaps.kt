package com.example.vrar.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

open class MapsView(context: Context, attributeSet: AttributeSet)  : View(context, attributeSet){
    lateinit var back : Bitmap
    var paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(
            back,
            Rect(0, 0, back.width, back.height),
            Rect(0, 0, width, height),
            paint
        ) // отрисовка карты
    }
}