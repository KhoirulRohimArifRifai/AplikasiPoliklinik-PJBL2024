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

class DokterUmumActivity : AppCompatActivity() {
    lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dokter_umum)

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
        val cardDok3 = findViewById<CardView>(R.id.dokter3)
        // Set klik listener untuk CardView
        cardDok3.setOnClickListener {
            showModalDok3()
        }
        val cardDok4 = findViewById<CardView>(R.id.dokter4)
        // Set klik listener untuk CardView
        cardDok4.setOnClickListener {
            showModalDok4()
        }
    }
    private fun showModalDok1() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Dokter")
            .setMessage("Nama: dr. Whendi Setyawan\n\n" +
                    "Spesialis: Dokter Poli Umum\n\n" +
                    "Status: Aktif\n\n" +
                    "Jadwal: Senin - Rabu : 09.00 - 12.00")
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
            .setMessage("Nama: dr. Lycosa Grace Puditasari\n\n" +
                    "Spesialis: Dokter Poli Umum\n\n" +
                    "Status: Aktif\n\n" +
                    "Jadwal: Kamis - Sabtu : 09.00 - 12.00")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
    private fun showModalDok3() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Dokter")
            .setMessage("Nama: dr. Christian Hans Suprapto\n\n" +
                    "Spesialis: Dokter Poli Umum\n\n" +
                    "Status: Aktif\n\n" +
                    "Jadwal: Senin - Rabu : 13.00 - 17.00")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
    private fun showModalDok4() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Dokter")
            .setMessage("Nama: dr. Thevan Christian Saputra\n\n" +
                    "Spesialis: Dokter Poli Umum\n\n" +
                    "Status: Aktif\n\n" +
                    "Jadwal: Kamis - Sabtu : 13.00 - 17.00")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
}