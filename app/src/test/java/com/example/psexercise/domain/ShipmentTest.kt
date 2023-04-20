package com.example.psexercise.domain

import org.junit.Assert.*
import org.junit.Test

class ShipmentTest {

    @Test
    fun `model should retrieve street name`() {
        val shipment = Shipment("100 Evergreen Terrace")

        val streetname = shipment.getStreetName()

        assertEquals("Evergreen Terrace", streetname)
    }

    @Test
    fun `model should tell if length of street name is even`() {
        val shipment = Shipment("100 Evergreen Terrace")

        val isEven = shipment.isEvenLenStreetName()

        assertFalse(isEven)
    }
}