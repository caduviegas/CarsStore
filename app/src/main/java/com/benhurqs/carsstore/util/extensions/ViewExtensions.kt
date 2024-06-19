package com.benhurqs.carsstore.util.extensions

import android.view.View
import androidx.core.view.isVisible



fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.remove(){
    visibility = View.GONE
}

fun View.enabled(){
    isVisible = true
}

fun View.disabled(){
    isVisible = false
}