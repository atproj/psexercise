package com.example.psexercise.domain

import org.junit.Assert
import org.junit.Test

class FindShipmentUseCaseTest {

    private val findShipmentUseCase  = FindShipmentUseCase()

    private val shipments = listOf(
        Shipment("215 Osinski Manors"),
        Shipment("9856 Marvin Stravenue"),
        Shipment("7127 Kathlyn Ferry"),
        Shipment("987 Champlin Lake"),
        Shipment("63187 Volkman Garden Suite 447"),
        Shipment("75855 Dessie Lights"),
        Shipment("1797 Adolf Island Apt. 744"),
        Shipment("2431 Lindgren Corners"),
        Shipment("8725 Aufderhar River Suite 859"),
        Shipment("79035 Shanna Light Apt. 322"),
    )

    @Test
    fun `test shipment for Everardo Welch`() {
        val driver = Driver("Everardo Welch")

        val ss = findShipmentUseCase.execute(shipments, driver)

        Assert.assertEquals("7127 Kathlyn Ferry", ss.address)
    }
}