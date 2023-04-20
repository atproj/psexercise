package com.example.psexercise.data

import android.content.Context
import com.example.psexercise.FileLoader
import com.example.psexercise.domain.Driver
import com.example.psexercise.domain.Shipment
import com.google.gson.Gson
import javax.inject.Inject

class MockDataSource @Inject constructor(context: Context, gson: Gson) : LocalDataSource {

    private val shipmentsAndDrivers: ShipmentsAndDrivers

    init {
        shipmentsAndDrivers = gson.fromJson(
            FileLoader.getJsonDataFromAsset(context, "data.json"),
            ShipmentsAndDrivers::class.java
        )
    }

    override fun loadDrivers(): List<Driver> {
        return shipmentsAndDrivers.drivers.map { driverStr -> Driver(driverStr) }.toList()
    }

    override fun loadShipments(): List<Shipment> {
        return shipmentsAndDrivers.shipments.map { addressStr -> Shipment(addressStr) }.toList()
    }

}