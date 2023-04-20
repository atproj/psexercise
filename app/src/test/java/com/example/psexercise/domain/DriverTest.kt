package com.example.psexercise.domain

import org.junit.Assert.*
import org.junit.Test

class DriverTest {

    @Test
    fun `model accurately returns the number of vowels in its name`() {
        val driver = Driver("Aaron Rodgers")

        val actual = driver.getNumberOfVowels()

        assertEquals(5, actual)
    }

    @Test
    fun `model accurately returns the number of consonants in its name`() {
        val driver = Driver("Aaron Rodgers")

        val actual = driver.getNumberOfConsonants()

        assertEquals(7,  actual)
    }
}