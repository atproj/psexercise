package com.example.psexercise.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psexercise.data.ShipmentRepository
import com.example.psexercise.di.IoDispatcher
import com.example.psexercise.domain.Driver
import com.example.psexercise.domain.FindShipmentUseCase
import com.example.psexercise.domain.Shipment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipmentsViewModel @Inject constructor(
    private val shipmentRepo: ShipmentRepository,
    private val findShipmentUseCase: FindShipmentUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher): ViewModel() {

    private val _shipmentState = MutableLiveData<ResultState<Shipment>>()
    val shipmentState: LiveData<ResultState<Shipment>> = _shipmentState

    private var shipments = emptyList<Shipment>()
    private val driverToShipment = mutableMapOf<Driver, Shipment>()

    fun initializeWith(driver: Driver) {
        viewModelScope.launch (dispatcher) {
            try {
                driverToShipment[driver]?.let {
                    _shipmentState.postValue(
                        ResultState.Success(it)
                    )
                    return@launch
                }
                if (shipments.isEmpty()) {
                    shipments = shipmentRepo.getShipments()
                }
                val bestShipment = findShipmentUseCase.execute(shipments, driver)
                driverToShipment.put(driver, bestShipment)
                shipments = shipments.minus(bestShipment)
                _shipmentState.postValue(
                    ResultState.Success(bestShipment)
                )
            } catch (ex: Exception) {
                _shipmentState.postValue(
                    ResultState.Failure("Failed to retrieve shipment", ex)
                )
            }
        }
    }
}