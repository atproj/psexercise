package com.example.psexercise.data

import com.example.psexercise.domain.Driver
import com.example.psexercise.domain.Shipment

interface LocalDataSource {
    fun loadDrivers(): List<Driver>
    fun loadShipments(): List<Shipment>
}