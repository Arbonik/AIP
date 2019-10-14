package com.example.vrar.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.vrar.R

class map(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val transition = ResourcesCompat.getDrawable(context.resources,R.drawable.cavemap,null)
    val img = 
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.

    }
}