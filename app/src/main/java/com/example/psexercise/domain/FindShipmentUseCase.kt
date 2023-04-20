package com.example.psexercise.domain

import javax.inject.Inject

class FindShipmentUseCase @Inject constructor() {

    companion object {
        private const val VOWELS_MULTIPLIER = 1.5f
    }

    fun execute(shipments: List<Shipment>, driver: Driver): Shipment {
        val maxShipment = shipments.map { shipment ->
            val bss = if (shipment.isEvenLenStreetName()) driver.getNumberOfVowels()
            else driver.getNumberOfConsonants()
            val isUpdate = (2 until minOf(
                shipment.getStreetName().length,
                driver.fullname.length
            )
                    ).any {
                    shipment.getStreetName().length % it == 0 &&
                            driver.fullname.length % it == 0
                }
            val ss = if (isUpdate) (bss * VOWELS_MULTIPLIER) else bss.toFloat()
            shipment to ss
        }.maxBy { pair -> pair.second }.first
        return maxShipment
    }
}