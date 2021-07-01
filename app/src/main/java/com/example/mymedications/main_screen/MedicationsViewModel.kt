package com.example.mymedications.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymedications.MedicationsMapper
import com.example.mymedications.MedicationsRepository
import com.example.mymedications.MedicationsRepositoryImpl
import com.example.mymedications.api.MedicationModel
import com.example.mymedications.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MedicationsViewModel : ViewModel() {

    private val repository: MedicationsRepository = MedicationsRepositoryImpl()
    private val mapper: MedicationsMapper = MedicationsMapper()


    private val medications = MutableLiveData<Resource<List<MedicationModel>>>()
    private val disposables = CompositeDisposable()

    init {
        loadMedicationsList()
    }

    private fun loadMedicationsList() {
        medications.postValue(Resource.loading(null))
        disposables.add(
            repository.getMedicationsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ medicationsList ->
                    medications.postValue(Resource.success(medicationsList.map(mapper::fromResponseToModel)))
                }, {
                    medications.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun reloadData() {
        loadMedicationsList()
    }

    fun getMedications(): LiveData<Resource<List<MedicationModel>>> {
        return medications
    }
}