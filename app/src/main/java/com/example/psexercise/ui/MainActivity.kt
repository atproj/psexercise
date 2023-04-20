package com.example.psexercise.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.psexercise.R
import com.example.psexercise.domain.Driver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    companion object{
        private const val DRIVERS_TAG = "drivers"
        private const val SHIPMENTS_TAG = "shipments"
    }

    private val viewmodel by viewModels<DriversViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel.selectedDriverState.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                navigateToShipmentFragment(it)
            }
        }
        if (savedInstanceState == null) {
            navigateToDriversFragment()
        }
    }

    private fun navigateToDriversFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container, DriversFragment(), DRIVERS_TAG
        )
            .commit()
    }

    private fun navigateToShipmentFragment(driver: Driver, addToBackstack: Boolean=true) {
        supportFragmentManager.beginTransaction().replace(
            R.id.container, ShipmentFragment.newInstance(driver), SHIPMENTS_TAG
        )
            .addToBackStack("to_shipment")
            .commit()
    }
}