package com.example.mymedications

import com.example.mymedications.api.MedicationModel
import com.example.mymedications.api.MedicationsInfoResponseType

class MedicationsMapper {

    fun fromResponseToModel(entity: MedicationsInfoResponseType): MedicationModel =
        MedicationModel(
            id = entity.id,
            title = entity.title,
            icon = entity.icon,
            isReadyForKids = entity.isReadyForKids
        )
}