package com.example.psexercise.ui

sealed class ResultState<out T: Any> {

    data class Success<out T: Any>(val value: T): ResultState<T>()
    data class Failure(val message: String, val ex: Exception? = null): ResultState<Nothing>()
    object Loading: ResultState<Nothing>()
}