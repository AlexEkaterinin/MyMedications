package com.example.mymedications

import com.example.mymedications.api.MedicationsApi
import com.example.mymedications.api.MedicationsApiService
import com.example.mymedications.api.MedicationsInfoResponseType
import io.reactivex.Single

interface MedicationsRepository {

    fun getMedicationsList(): Single<List<MedicationsInfoResponseType>>
}

class MedicationsRepositoryImpl() : MedicationsRepository {

    private val api: MedicationsApi = MedicationsApiService.buildApiService()

    override fun getMedicationsList(): Single<List<MedicationsInfoResponseType>> =
        api.getMedicationsList()
}