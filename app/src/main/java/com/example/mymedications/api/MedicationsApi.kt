package com.example.mymedications.api

import io.reactivex.Single
import retrofit2.http.GET

interface MedicationsApi {
    @GET("b168786f-720f-4f30-8fba-a0345d49823a")
    fun getMedicationsList(): Single<List<MedicationsInfoResponseType>>
}