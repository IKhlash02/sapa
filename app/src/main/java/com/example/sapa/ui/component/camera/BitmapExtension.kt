package com.example.sapa.ui.component.camera

import android.graphics.Bitmap

fun Bitmap.centerCrop(desirtedWidth: Int, desiredHeight: Int) : Bitmap {

    val xStart = (width - desirtedWidth) /2
    val yStart = (height - desiredHeight) /2

    if(xStart < 0 || yStart < 0 || desirtedWidth > width || desiredHeight > height){
        throw IllegalArgumentException("Invalid arguments for center croping")
    }

    return  Bitmap.createBitmap(this, xStart, yStart, desirtedWidth, desiredHeight)

}