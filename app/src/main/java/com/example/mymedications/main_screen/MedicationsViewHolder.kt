package com.example.mymedications.main_screen

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymedications.R
import com.example.mymedications.api.MedicationModel
import com.squareup.picasso.Picasso

class MedicationsViewHolder(
    itemView: View,
    private val showMedicationInfoListener: (medication: MedicationModel) -> Unit
): RecyclerView.ViewHolder(itemView) {

    private var icon: ImageView = itemView.findViewById(R.id.medication_icon)
    private var title: TextView = itemView.findViewById(R.id.medication_title)
    private lateinit var medication: MedicationModel

    init {
        itemView.setOnClickListener() {
            showMedicationInfoListener.invoke(medication)
        }
    }


    fun bind(item: MedicationModel) {
        this.medication = item

        Picasso.get()
            .load(item.icon)
            .into(icon)

        title.text = item.title
    }
}