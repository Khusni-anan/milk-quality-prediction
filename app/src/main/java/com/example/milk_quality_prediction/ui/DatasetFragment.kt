package com.example.milk_quality_prediction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.milk_quality_prediction.R

class DatasetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dataset, container, false)

        // Sesuaikan padding dengan status bar / navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Deskripsi
        setCard(view, R.id.card_desc, "Deskripsi", "Dataset ini berisi data kualitas susu berdasarkan hasil pengujian seperti pH, suhu, rasa, bau, lemak, kekeruhan, dan warna. Setiap entri memiliki label kualitas: Low, Medium, atau High.")
        return view
    }

    private fun setCard(root: View, viewId: Int, title: String, content: String) {
        val cardView = root.findViewById<View>(viewId)
        cardView.findViewById<TextView>(R.id.cardTitle).text = title
        cardView.findViewById<TextView>(R.id.cardContent).text = content
    }
}
