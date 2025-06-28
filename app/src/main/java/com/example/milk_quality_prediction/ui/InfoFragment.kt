package com.example.milk_quality_prediction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.milk_quality_prediction.R

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        // Tombol kembali
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Inset padding (untuk status bar dll)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set isi kartu
        setCard(view, R.id.card_dataset, "Sumber Dataset",
            "Dataset berasal dari Kaggle oleh pengguna cpluzshrijayan dengan judul Milk Quality Prediction.\nhttps://www.kaggle.com/datasets/cpluzshrijayan/milkquality")

        setCard(view, R.id.card_cara_kerja, "Cara Kerja Aplikasi",
            "- Dataset: Melihat data mentah.\n- Fitur: Melihat fitur yang digunakan dalam pemodelan.\n- Model: Menampilkan proses pelatihan model.\n- Simulasi: Menginput data dan melihat hasil prediksi kualitas susu.")
        val linkCard = view.findViewById<View>(R.id.card_dataset)
        linkCard.findViewById<TextView>(R.id.cardContent)?.apply {
            movementMethod = android.text.method.LinkMovementMethod.getInstance()
        }


        setCard(view, R.id.card_pembuat, "👤 Dibuat oleh", "Khusni Anan")

        return view
    }

    private fun setCard(root: View, viewId: Int, title: String, content: String) {
        val card = root.findViewById<View>(viewId)
        card.findViewById<TextView>(R.id.cardTitle).text = title
        card.findViewById<TextView>(R.id.cardContent).text = content
    }
}
