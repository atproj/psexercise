package com.example.psexercise.data

import androidx.annotation.VisibleForTesting
import com.example.psexercise.di.IoDispatcher
import com.example.psexercise.domain.Driver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DriverRepository @Inject constructor(
    private val mockDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher) {

    var drivers: List<Driver> = listOf()
        private set

    suspend fun getDrivers(): List<Driver> {
        if (drivers.isNotEmpty()) return drivers

        return withContext(dispatcher) {
            drivers = mockDataSource.loadDrivers()
            drivers
        }
    }

    @VisibleForTesting
    fun setDrivers(drivers: List<Driver>) {
        this.drivers = drivers
    }
}