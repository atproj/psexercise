package com.example.psexercise.data

import com.example.psexercise.domain.Shipment
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ShipmentRepositoryTest {

    private val dataSrc = mockk<LocalDataSource>()
    private val repo = ShipmentRepository(dataSrc, UnconfinedTestDispatcher())

    @Test
    fun `repo successfully fetches list of shipments`() = runTest {
        val shipments = listOf<Shipment>()
        coEvery { dataSrc.loadShipments() } returns shipments

        val actual = repo.getShipments()

        Assert.assertEquals(shipments, actual)
    }

    @Test(expected = Exception::class)
    fun `repo fails to fetch list of shipments`() = runTest {
        coEvery { dataSrc.loadShipments() } throws IOException()

        repo.getShipments()
    }
}