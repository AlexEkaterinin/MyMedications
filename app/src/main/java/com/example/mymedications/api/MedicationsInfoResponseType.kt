package com.example.mymedications.api

import com.google.gson.annotations.SerializedName

open class MedicationsInfoResponseType(
    @SerializedName ("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("isReadyForKids")
    val isReadyForKids: Boolean
)