package com.rifai.aplikasipoliklinik

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DokterGigiActivity : AppCompatActivity() {
    lateinit var back: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dokter_gigi)

        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, DokterActivity::class.java)
            startActivity(intent)
        }
        val cardDok1 = findViewById<CardView>(R.id.dokter1)
        // Set klik listener untuk CardView
        cardDok1.setOnClickListener {
            showModalDok1()
        }
        val cardDok2 = findViewById<CardView>(R.id.dokter2)
        // Set klik listener untuk CardView
        cardDok2.setOnClickListener {
            showModalDok2()
        }
    }
    private fun showModalDok1() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Dokter")
            .setMessage("Nama: drg. Arfita Ajeng Dewi Rakasiwi\n\n" +
                    "Spesialis: Dokter Gigi Umum\n\n" +
                    "Status: Aktif\n\n" +
                    "Jadwal: Senin - Rabu : 09.00 - 14.00")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }

    private fun showModalDok2() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Dokter")
            .setMessage("Nama: drg. Citra Adinda RA Putri, Sp. KGA\n\n" +
                    "Spesialis: Dokter Gigi Anak\n\n" +
                    "Status: Aktif\n\n" +
                    "Jadwal: Kamis - Sabtu : 15.00 - 20.00")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
}