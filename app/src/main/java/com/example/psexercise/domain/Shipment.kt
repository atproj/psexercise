package com.example.psexercise.domain

class Shipment(val address: String) {
    /**
     * Parses the street name from the full address.  For simplicity assume the street name
     * is found by adding the two words after the first number.
     */
    fun getStreetName(): String {
        val parts = address.split(" ")
        if (parts.size < 2) {
            throw IllegalArgumentException("Address be at least 2 words")
        }
        if (!address[0].isDigit()) {
            throw IllegalArgumentException("Address must start with a number")
        }
        return parts[1] + " " + parts[2]
    }

    fun isEvenLenStreetName() = getStreetName().length%2 == 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shipment

        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        return address.hashCode()
    }
}