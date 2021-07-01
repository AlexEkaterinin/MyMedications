package com.example.mymedications.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymedications.R
import com.example.mymedications.api.MedicationModel
import com.example.mymedications.databinding.MedicationsListFragmentBinding
import com.example.mymedications.isVisible
import com.example.mymedications.utils.Status

class MedicationsListFragment: Fragment() {

    private lateinit var binding: MedicationsListFragmentBinding
    private val medicationsViewModel: MedicationsViewModel by viewModels()

    private val medicationsAdapter: MedicationsListAdapter by lazy {
        MedicationsListAdapter(
            showMedicationInfoListener = {
                medication: MedicationModel ->
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentsContainer, MedicationDetailInfoFragment.newInstance(medication.title))
                    .addToBackStack(MedicationDetailInfoFragment::class.java.canonicalName)
                    .commit()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MedicationsListFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMedications = binding.rvMedicationsList

        rvMedications.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = medicationsAdapter
        }

        binding.refreshBtn.setOnClickListener() {
            medicationsViewModel.reloadData()
        }

        setupObserveData()
    }

    private fun setupObserveData() {
        medicationsViewModel.getMedications().observe(requireActivity(), Observer { loadData ->
            when(loadData.status) {
                Status.SUCCESS -> {
                    binding.progressBar.isVisible(false)
                    binding.rvMedicationsList.isVisible(true)
                    loadData.data?.let { medicationsAdapter.setData(it) }
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible(true)
                    binding.rvMedicationsList.isVisible(false)
                    binding.errorMessage.isVisible(false)

                }
                Status.ERROR -> {
                    binding.progressBar.isVisible(false)
                    showErrorMessage()
                }
            }
        })
    }

    private fun showErrorMessage() {
        binding.rvMedicationsList.isVisible(false)
        binding.errorMessage.isVisible(true)
    }
}