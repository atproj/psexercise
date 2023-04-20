package com.example.psexercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.psexercise.R
import com.example.psexercise.domain.Driver

class DriversAdapter(private val drivers: List<Driver>,
                     private val onDriverSelected: (driver: Driver)->Unit):
    RecyclerView.Adapter<DriversAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTV = itemView.findViewById(R.id.driverTV) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_driver, parent, false)
        ).also { viewholder ->
            viewholder.itemView.setOnClickListener {
                onDriverSelected.invoke(drivers[viewholder.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTV.text = drivers[position].fullname
    }

    override fun getItemCount() = drivers.size
}