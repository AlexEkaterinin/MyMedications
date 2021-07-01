package com.example.mymedications.api

data class MedicationModel(
    val id: Int,
    val title: String,
    val icon: String,
    val isReadyForKids: Boolean
)
