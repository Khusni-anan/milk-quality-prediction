package com.example.milk_quality_prediction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.milk_quality_prediction.R
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimulasiFragment : Fragment() {

    private lateinit var interpreter: Interpreter
    private lateinit var tvResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_simulasi, container, false)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol kembali
        view.findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        tvResult = view.findViewById(R.id.tv_result)
        val btnCek = view.findViewById<Button>(R.id.btn_cek)

        interpreter = Interpreter(loadModelFile("milk_grade_predict.tflite"))

        btnCek.setOnClickListener {
            runPrediction(view)
        }

        return view
    }

    private fun runPrediction(view: View) {
        try {
            val ph = view.findViewById<EditText>(R.id.et_ph).text.toString().toFloat()
            val temp = view.findViewById<EditText>(R.id.et_temp).text.toString().toFloat()
            val colour = view.findViewById<EditText>(R.id.et_colour).text.toString().toFloat()

            val taste = if (view.findViewById<RadioButton>(R.id.taste_1).isChecked) 1f else 0f
            val odor = if (view.findViewById<RadioButton>(R.id.odor_1).isChecked) 1f else 0f
            val fat = if (view.findViewById<RadioButton>(R.id.fat_1).isChecked) 1f else 0f
            val turbidity = if (view.findViewById<RadioButton>(R.id.turbidity_1).isChecked) 1f else 0f

            val input = floatArrayOf(
                (ph - 3f) / (9.5f - 3f),
                (temp - 34f) / (90f - 34f),
                taste,
                odor,
                fat,
                turbidity,
                (colour - 240f) / (255f - 240f)
            )

            val inputBuffer = ByteBuffer.allocateDirect(4 * input.size).order(ByteOrder.nativeOrder())
            input.forEach { inputBuffer.putFloat(it) }
            inputBuffer.rewind()

            val outputBuffer = ByteBuffer.allocateDirect(4 * 3).order(ByteOrder.nativeOrder())
            interpreter.run(inputBuffer, outputBuffer)
            outputBuffer.rewind()

            val result = FloatArray(3)
            outputBuffer.asFloatBuffer().get(result)

            val maxIndex = result.indices.maxByOrNull { result[it] } ?: -1
            val label = when (maxIndex) {
                0 -> "HIGH"
                1 -> "LOW"
                2 -> "MEDIUM"
                else -> "UNKNOWN"
            }

            tvResult.text =
            "Kualitas Susu: $label"
            .trimIndent()

        } catch (e: Exception) {
            tvResult.text = "Masukkan semua data dengan benar!"
        }
    }

    private fun loadModelFile(fileName: String): MappedByteBuffer {
        val fileDescriptor = requireContext().assets.openFd(fileName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }
}
