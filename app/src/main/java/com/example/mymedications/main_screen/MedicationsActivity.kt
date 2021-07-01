package com.example.mymedications.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.mymedications.R
import com.example.mymedications.databinding.MedicationsActivityBinding


class MedicationsActivity : AppCompatActivity() {

    private lateinit var binding: MedicationsActivityBinding
    private val medicationListFragment = MedicationsListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MedicationsActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(savedInstanceState == null) {
            showMedicationsListFragment()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

    private fun showMedicationsListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentsContainer, medicationListFragment)
            .addToBackStack(medicationListFragment::class.java.canonicalName)
            .commit()
    }
}