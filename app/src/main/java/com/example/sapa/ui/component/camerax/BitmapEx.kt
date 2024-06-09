package com.example.sapa.ui.component.camerax

import android.graphics.Bitmap

fun Bitmap.centerCrop(desireWidth: Int, desireHeight: Int): Bitmap{
    val xStart = (width - desireWidth)/2
    val yStart = (width - desireWidth)/2

    if( xStart < 0 || yStart <0 || desireWidth > width || desireHeight > height){
        throw IllegalArgumentException("Invalid Argument for center croping")
    }


    return Bitmap.createBitmap(this, xStart, yStart, desireWidth, desireHeight)
}