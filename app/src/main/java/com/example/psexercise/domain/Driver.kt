package com.example.psexercise.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Driver(val fullname: String): Parcelable {
    companion object {
        private val VOWELS = hashSetOf(
            'A', 'a',
            'E', 'e',
            'I', 'i',
            'O', 'o',
            'U', 'u'
        )
    }

    fun getNumberOfVowels() = fullname.filter { VOWELS.contains(it) }.length

    fun getNumberOfConsonants() = fullname.split("\\s").sumOf { it.length } -
            getNumberOfVowels() - 1

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Driver

        if (fullname != other.fullname) return false

        return true
    }

    override fun hashCode(): Int {
        return fullname.hashCode()
    }
}