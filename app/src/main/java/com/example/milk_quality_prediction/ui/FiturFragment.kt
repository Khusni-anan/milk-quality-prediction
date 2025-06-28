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

class FiturFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_fitur, container, false)

        // Handle inset untuk status bar / notch
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setCard(view, R.id.card_ph, "1) pH",
            "• Tipe Data: Float\n• Bentuk: Numerik\n• pH mengukur tingkat keasaman atau kebasaan susu. Susu segar biasanya memiliki pH netral di kisaran 6.5–6.8. pH yang terlalu rendah bisa mengindikasikan fermentasi atau kerusakan susu akibat pertumbuhan bakteri.")

        setCard(view, R.id.card_temp, "2) Temperature",
            "• Tipe Data: Float\n• Bentuk: Numerik\n• Temperature adalah suhu susu dalam satuan derajat Celsius (°C). Suhu yang tinggi dapat mempercepat pertumbuhan mikroorganisme dan mempercepat pembusukan, sedangkan suhu rendah (sekitar 4°C) membantu menjaga kesegaran susu.")

        setCard(view, R.id.card_taste, "3) Taste",
            "• Tipe Data: Integer (0/1)\n• Bentuk: Boolean\n• Taste menunjukkan apakah rasa susu masih normal. Nilai 1 berarti rasa normal, sedangkan nilai 0 berarti rasa tidak normal, seperti asam atau basi—yang bisa menandakan proses fermentasi telah terjadi.")

        setCard(view, R.id.card_odor, "4) Odor",
            "• Tipe Data: Integer (0/1)\n• Bentuk: Boolean\n• Odor menunjukkan kondisi bau dari susu. Nilai 1 berarti bau normal atau segar, dan nilai 0 menunjukkan bau tidak sedap, seperti bau busuk atau menyengat akibat kontaminasi atau pembusukan.")

        setCard(view, R.id.card_fat, "5) Fat",
            "• Tipe Data: Integer (0/1)\n• Bentuk: Boolean\n• Fat menunjukkan keberadaan lemak dalam susu. Nilai 1 berarti susu mengandung lemak (seperti susu murni atau full cream), sedangkan 0 berarti tidak mengandung lemak atau susu skim. Lemak sangat berperan dalam memberikan rasa gurih dan kandungan nutrisi.")

        setCard(view, R.id.card_turbidity, "6) Turbidity",
            "• Tipe Data: Integer (0/1)\n• Bentuk: Boolean\n• Turbidity mengukur tingkat kekuruhan susu. Nilai 1 berarti tingkat kekeruhan normal, yang biasanya berasal dari partikel protein dan lemak. Sedangkan nilai 0 berarti susu tidak keruh atau terlalu jernih, yang dapat mengindikasikan adanya pengenceran atau kualitas rendah.")

        setCard(view, R.id.card_colour, "7) Colour",
            "• Tipe Data: Integer\n• Bentuk: Numerik\n• Colour merepresentasikan intensitas warna susu dalam skala 240–255. Semakin tinggi nilainya, semakin terang warna susu. Nilai 240 menunjukkan warna yang lebih gelap (krem pucat), sedangkan 255 menunjukkan warna putih bersih yang dianggap normal. Perubahan warna di luar rentang ini bisa menandakan kontaminasi atau proses oksidasi.")

        return view
    }

    private fun setCard(root: View, viewId: Int, title: String, content: String) {
        val cardView = root.findViewById<View>(viewId)
        cardView.findViewById<TextView>(R.id.cardTitle).text = title
        cardView.findViewById<TextView>(R.id.cardContent).text = content
    }
}
