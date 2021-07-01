package com.example.mymedications.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymedications.databinding.MedicationInfoFragmentBinding

class MedicationDetailInfoFragment : Fragment() {

    private lateinit var binding: MedicationInfoFragmentBinding

    private val medicationTitle: String by lazy {
        requireArguments().getString(ARG_MED_TITLE)
            ?: throw IllegalArgumentException("Medications title can not be null")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MedicationInfoFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.medicationTitle.text = medicationTitle

        binding.medicationsInfoToolbar.run {
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    companion object {
        const val ARG_MED_TITLE = "arg_med_title"

        fun newInstance(medicationTitle: String) =
            MedicationDetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MED_TITLE, medicationTitle)
                }
            }
    }
}