package com.example.psexercise.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.psexercise.R
import com.example.psexercise.domain.Driver
import com.google.android.material.snackbar.Snackbar

class ShipmentFragment: Fragment() {

    private val viewmodel by activityViewModels<ShipmentsViewModel>()

    companion object {
        private const val SELECTED_DRIVER = "selected_driver"

        fun newInstance(driver: Driver): ShipmentFragment {
            return ShipmentFragment().apply {
                arguments = bundleOf(SELECTED_DRIVER to driver)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_shipment, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.shipmentToolbar)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        requireArguments().getParcelable<Driver>(SELECTED_DRIVER)?.let { driver ->
            viewmodel.initializeWith(driver)
        }

        viewmodel.shipmentState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultState.Loading -> {}
                is ResultState.Success ->
                    view.findViewById<TextView>(R.id.shipmentTV).text = result.value.address
                is ResultState.Failure ->
                    Snackbar.make(view, "Failed to determine best shipment!", 1000)
                        .show()
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        view?.findViewById<Toolbar>(R.id.shipmentToolbar)?.let {
            it.title = requireArguments().getParcelable<Driver>(SELECTED_DRIVER)?.fullname
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}