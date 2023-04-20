package com.example.psexercise.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psexercise.R
import com.example.psexercise.domain.Driver
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriversFragment : Fragment() {

    private lateinit var progressBar: ProgressBar

    private val viewmodel by activityViewModels<DriversViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_drivers, container, false)
        progressBar = view.findViewById(R.id.pb)
        val toolbar = view.findViewById<Toolbar>(R.id.driversToolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        viewmodel.driversState.observe(viewLifecycleOwner) { driversResult ->
            when (driversResult) {
                is ResultState.Loading -> progressBar.visibility = View.VISIBLE
                is ResultState.Success -> displayList(driversResult.value)
                is ResultState.Failure -> displayError(driversResult.message)
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        view?.findViewById<Toolbar>(R.id.driversToolbar)?.let {
            it.title = "Drivers"
        }
    }

    private fun displayList(drivers: List<Driver>) {
        progressBar.visibility = View.GONE
        view?.rootView?.findViewById<RecyclerView>(R.id.rv)?.apply {
            addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL)
            )
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = DriversAdapter(drivers) { selection ->
                viewmodel.setSelectedDriver(selection)
            }
        }
    }

    private fun displayError(message: String) {
        progressBar.visibility = View.GONE
        Snackbar.make(progressBar.rootView, message, 1000).show()
    }
}