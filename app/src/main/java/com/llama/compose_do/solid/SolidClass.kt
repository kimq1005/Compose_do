package com.llama.compose_do.solid

import android.util.Log

interface Shape {
    fun draw()
}

class Circle: Shape {
    override fun draw() {
        Log.d("TAG", "draw: let's Circle draw")
    }
}

class Square: Shape {
    override fun draw() {
        Log.d("TAG", "draw: let's Square draw")
    }
}