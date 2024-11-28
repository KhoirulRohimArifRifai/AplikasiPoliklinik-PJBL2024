package com.rifai.aplikasipoliklinik

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DialogEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dialog_edit)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val tglDaftar = findViewById<EditText>(R.id.edittgl_daftar)

        // Set OnClickListener untuk memunculkan DatePickerDialog
        tglDaftar.setOnClickListener {
            // Ambil tanggal saat ini untuk digunakan sebagai default
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Tampilkan DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Format tanggal yang dipilih dan tampilkan di EditText
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    tglDaftar.setText(formattedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }
    }
}