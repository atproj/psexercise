package com.example.psexercise.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.psexercise.data.DriverRepository
import com.example.psexercise.domain.Driver
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.*
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test

class DriversViewModelTest {

    private var repository = mockk<DriverRepository>(relaxed = true)
    private var viewmodel = DriversViewModel(repository)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `driversState LiveData is updated with the result from DriverRepository`() = runTest {
        val drivers = listOf(mockk<Driver>(relaxed = true))
        val success = ResultState.Success(drivers)
        coEvery { repository.getDrivers() } returns drivers

        viewmodel.initialize()

        assertEquals(success, viewmodel.driversState.value)
    }

    @Test
    fun `selecting a driver should updated the selectedDriverState LiveData`() = runTest {
        val driver = mockk<Driver>(relaxed = true)

        viewmodel.setSelectedDriver(driver)

        assertEquals(driver, (viewmodel.selectedDriverState.value as Event).peekContent())
    }
}