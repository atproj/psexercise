package com.example.psexercise.data

import com.example.psexercise.domain.Driver
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class DriverRepositoryTest {

    private val dataSrc = mockk<LocalDataSource>()
    private val repo = DriverRepository(dataSrc, UnconfinedTestDispatcher())

    @Test
    fun `repo successfully fetches list of drivers from local data source`() = runTest {
        val drivers = listOf<Driver>()
        coEvery { dataSrc.loadDrivers() } returns drivers

        val actual = repo.getDrivers()

        assertEquals(drivers, actual)
    }

    @Test
    fun `repo fetches list of drivers from memory cache`() = runTest {
        val cache = listOf(Driver(""))
        repo.setDrivers(cache)

        val actual = repo.getDrivers()

        assertEquals(cache, actual)
    }

    @Test(expected = Exception::class)
    fun `repo fails to fetch list of drivers from local data source`() = runTest {
        coEvery { dataSrc.loadDrivers() } throws IllegalArgumentException()

        repo.getDrivers()
    }
}