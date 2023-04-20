package com.example.psexercise.data

import com.example.psexercise.di.IoDispatcher
import com.example.psexercise.domain.Shipment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShipmentRepository @Inject constructor(
    private val mockDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getShipments(): List<Shipment> {
        return withContext(dispatcher) {
            mockDataSource.loadShipments()
        }
    }
}