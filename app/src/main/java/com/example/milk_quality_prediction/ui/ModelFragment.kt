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

class ModelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_model, container, false)

        // Atur padding agar tidak bentrok dengan status bar
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set isi untuk masing-masing card
        setCard(
            view,
            R.id.card_model_desc,
            "Deskripsi Model",
            "Model ini menggunakan algoritma Artificial Neural Network (ANN) untuk memprediksi kualitas susu berdasarkan fitur seperti pH, suhu, bau, dan lainnya. Data diproses terlebih dahulu untuk hasil prediksi yang lebih akurat."
        )

        setCard(
            view,
            R.id.card_data_prep,
            "📌 Data Preparation",
            "• Label Encoding untuk kolom Grade\n• Normalisasi fitur menggunakan MinMaxScaler\n• Split data: 80% training, 20% testing"
        )

        setCard(
            view,
            R.id.card_architecture,
            "🧠 Arsitektur ANN",
            "Input Layer: 7 fitur\nHidden Layers: [128, 64, 32] neuron, aktivasi ReLU\nOutput Layer: 3 neuron (softmax)\nLoss: sparse_categorical_crossentropy\nOptimizer: Adam\nEpochs: 50, Batch size: 16"
        )

        setCard(
            view,
            R.id.card_training_result,
            "📈 Hasil Pelatihan",
            "Model mencapai akurasi tinggi (>90%) pada data uji dengan performa baik dalam mengklasifikasi kualitas susu ke dalam kelas low, medium, dan high.",
            terminalOutput = """
                7/7 ──────────────────────────────── 70ms/step - accuracy: 0.9466 - loss: 0.0905
                Akurasi Model : 0.9670
                Loss Model    : 0.0713
            """.trimIndent()
        )

        return view
    }

    private fun setCard(
        parent: View,
        viewId: Int,
        title: String,
        content: String,
        terminalOutput: String? = null
    ) {
        val cardView = parent.findViewById<View>(viewId)
        val titleView = cardView.findViewById<TextView>(R.id.cardTitle)
        val contentView = cardView.findViewById<TextView>(R.id.cardContent)

        titleView.text = title
        contentView.text = content

        // Jika ada terminal output, tampilkan
        val terminalView = cardView.findViewById<TextView?>(R.id.terminalOutput)
        if (terminalView != null && terminalOutput != null) {
            terminalView.text = terminalOutput
            terminalView.visibility = View.VISIBLE
        }
    }
}
