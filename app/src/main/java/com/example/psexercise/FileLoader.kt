package com.example.psexercise

import android.content.Context
import android.util.Log
import java.io.IOException

object FileLoader {
    fun getJsonDataFromAsset(context: Context, filename: String): String? {
        return try {
            context.assets.open(filename).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            Log.d("TRACE", "error reading from $filename")
            null
        }
    }
}