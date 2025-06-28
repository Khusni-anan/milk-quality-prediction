package com.example.milk_quality_prediction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.milk_quality_prediction.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnSimulasi = view.findViewById<Button>(R.id.btnSimulasi)
        val btnInfo = view.findViewById<ImageButton>(R.id.btnInfo)

        btnSimulasi.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_simulasiFragment)
        }

        btnInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }

        return view
    }
}
