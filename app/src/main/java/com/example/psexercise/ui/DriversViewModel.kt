package com.example.psexercise.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psexercise.domain.Driver
import com.example.psexercise.data.DriverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriversViewModel @Inject constructor(private val driverRepo: DriverRepository): ViewModel() {

    private val _driversState = MutableLiveData<ResultState<List<Driver>>>()
    val driversState: LiveData<ResultState<List<Driver>>> = _driversState

    private val _selectedDriverState = MutableLiveData<Event<Driver>>()
    val selectedDriverState: LiveData<Event<Driver>> = _selectedDriverState

    fun setSelectedDriver(driver: Driver) {
        _selectedDriverState.value = Event(driver)
    }

    init {
        initialize()
    }

    fun initialize() {
        viewModelScope.launch {
            _driversState.postValue(ResultState.Loading)
            try {
                _driversState.postValue(
                    ResultState.Success(driverRepo.getDrivers())
                )
            } catch (ex: Exception) {
                _driversState.postValue(
                    ResultState.Failure("Failed to fetch drivers", ex)
                )
            }
        }
    }
}