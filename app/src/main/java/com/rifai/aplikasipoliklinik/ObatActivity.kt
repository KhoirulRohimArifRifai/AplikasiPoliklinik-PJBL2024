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

class ObatActivity : AppCompatActivity() {
    lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_obat)
        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Temukan CardView berdasarkan ID
        val cardFlu = findViewById<CardView>(R.id.flu)
        // Set klik listener untuk CardView
        cardFlu.setOnClickListener {
            showModalFlu()
        }
        val cardDalam = findViewById<CardView>(R.id.dalam)
        // Set klik listener untuk CardView
        cardDalam.setOnClickListener {
            showModalDalam()
        }
        val cardDiare = findViewById<CardView>(R.id.diare)
        // Set klik listener untuk CardView
        cardDiare.setOnClickListener {
            showModalDiare()
        }
        val cardGigi = findViewById<CardView>(R.id.gigi)
        // Set klik listener untuk CardView
        cardGigi.setOnClickListener {
            showModalGigi()
        }
    }
    private fun showModalFlu() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Obat Penyakit Flu")
            .setMessage(
                """Beberapa obat yang dapat membantu meredakan gejala flu:
                    
1. Paracetamol: Untuk meredakan demam dan nyeri tubuh.
2. Ibuprofen: Untuk mengurangi peradangan dan demam.
3. Dekongestan: Untuk mengurangi hidung tersumbat (contoh: Pseudoephedrine).
4. Antihistamin: Untuk meredakan hidung meler dan bersin-bersin.
            
Penting: Konsultasikan dengan dokter sebelum mengonsumsi obat-obatan, terutama jika Anda memiliki kondisi medis tertentu atau sedang mengonsumsi obat lain.
                """
            )
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
    private fun showModalDalam() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Obat Penyakit Dalam")
            .setMessage(
                """Berikut adalah beberapa obat yang umum digunakan untuk penyakit dalam:

1. Hipertensi (Tekanan Darah Tinggi):
        - Amlodipine: Obat penghambat saluran kalsium untuk menurunkan tekanan darah.
        - Losartan: Obat angiotensin II receptor blocker (ARB) yang digunakan untuk mengontrol tekanan darah tinggi.
   
2. Diabetes (Gula Darah Tinggi):
        - Metformin: Obat yang membantu menurunkan kadar gula darah dengan meningkatkan sensitivitas tubuh terhadap insulin.
        - Insulin: Obat suntik yang digunakan untuk mengontrol gula darah pada penderita diabetes tipe 1 dan tipe 2.
   
3. Penyakit Jantung (Angina, Gagal Jantung):
        - Bisoprolol: Beta-blocker yang digunakan untuk mengontrol detak jantung dan tekanan darah.
            
Penting: Konsultasikan dengan dokter sebelum mengonsumsi obat-obatan, terutama jika Anda memiliki kondisi medis tertentu atau sedang mengonsumsi obat lain.
                """
            )
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
    private fun showModalDiare() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Obat Penyakit Diare")
            .setMessage(
                """Berikut adalah beberapa obat yang umum digunakan untuk penyakit diare:

1. Obat Antidiarrheal (Penghenti Diare):
        - Loperamide (Imodium): Obat yang bekerja memperlambat gerakan usus, mengurangi frekuensi buang air besar, dan mengurangi cairan dalam tinja.

2. Antibiotik (Jika Diare Disebabkan Oleh Infeksi Bakteri):
        - Ciprofloxacin: Antibiotik dari golongan fluoroquinolone yang digunakan untuk mengobati diare yang disebabkan oleh infeksi bakteri seperti Salmonella
        - Azithromycin: Antibiotik yang sering digunakan untuk diare yang disebabkan oleh infeksi bakteri, termasuk bakteri Campylobacter.

3. Probiotik (Bakteri Baik untuk Pemulihan Usus):
        - Saccharomyces boulardii: Suplemen probiotik yang membantu menyeimbangkan mikroflora usus, mendukung pemulihan setelah diare yang disebabkan oleh antibiotik atau infeksi.
            
Penting: Konsultasikan dengan dokter sebelum mengonsumsi obat-obatan, terutama jika Anda memiliki kondisi medis tertentu atau sedang mengonsumsi obat lain.
                """
            )
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
    private fun showModalGigi() {
        // Membuat AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Informasi Obat Penyakit Gigi")
            .setMessage(
                """Berikut adalah beberapa obat yang umum digunakan untuk penyakit gigi:

1. Obat Penghilang Rasa Sakit (Analgesik):
        - Aspirin: Digunakan untuk meredakan rasa sakit dan peradangan pada gigi.

2. Obat Antibiotik (Untuk Infeksi Gigi atau Gusi):
        - Amoxicillin: Antibiotik golongan penisilin yang sering diresepkan untuk mengatasi infeksi gigi
        - Metronidazole: Diberikan untuk infeksi gigi yang disebabkan oleh bakteri anaerob

3. Obat Pengobatan Radang Gusi (Gingivitis):
        - Chlorhexidine: Obat kumur antiseptik yang digunakan untuk mengurangi plak dan peradangan pada gusi
            
Penting: Konsultasikan dengan dokter sebelum mengonsumsi obat-obatan, terutama jika Anda memiliki kondisi medis tertentu atau sedang mengonsumsi obat lain.
                """
            )
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss() // Menutup modal ketika tombol OK ditekan
            }
            .create()

        // Menampilkan modal dialog
        alertDialog.show()
    }
}