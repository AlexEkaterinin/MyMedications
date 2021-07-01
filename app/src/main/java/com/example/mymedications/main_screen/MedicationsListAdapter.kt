package com.example.mymedications.main_screen

import DiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymedications.R
import com.example.mymedications.api.MedicationModel

class MedicationsListAdapter(
    private val showMedicationInfoListener: (medication: MedicationModel) -> Unit
): RecyclerView.Adapter<MedicationsViewHolder>() {

    private val medicationsList: MutableList<MedicationModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medication_item, parent, false)
        return MedicationsViewHolder(
            view,
            showMedicationInfoListener
        )
    }

    override fun onBindViewHolder(holder: MedicationsViewHolder, position: Int) {
        holder.bind(medicationsList[position])
    }

    override fun getItemCount(): Int {
        return medicationsList.size
    }

    fun setData(list: List<MedicationModel>) {
        val oldData = medicationsList.toList()
        medicationsList.clear()
        medicationsList.addAll(list)

        DiffUtil
            .calculateDiff(DiffCallback(medicationsList, oldData), false)
            .dispatchUpdatesTo(this)
    }
}