package com.rifai.aplikasipoliklinik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val ambulanceCard: CardView = findViewById(R.id.ambulance)
        ambulanceCard.setOnClickListener {
            val intent = Intent(this, AmbulanceActivity::class.java)
            startActivity(intent)
        }
        val regisCard: CardView = findViewById(R.id.registrasi)
        regisCard.setOnClickListener {
            val intent = Intent(this, PendaftaranActivity::class.java)
            startActivity(intent)
        }
        val dokterCard: CardView = findViewById(R.id.dokter)
        dokterCard.setOnClickListener {
            val intent = Intent(this, DokterActivity::class.java)
            startActivity(intent)
        }
        val obatCard: CardView = findViewById(R.id.obat)
        obatCard.setOnClickListener {
            val intent = Intent(this, ObatActivity::class.java)
            startActivity(intent)
        }
        val jadwalCard: CardView = findViewById(R.id.jadwal)
        jadwalCard.setOnClickListener {
            val intent = Intent(this, JadwalDaftarActivity::class.java)
            startActivity(intent)
        }

        val cardConsultation = findViewById<CardView>(R.id.card_consultation)
        cardConsultation.setOnClickListener {
            val phoneNumber = "6285280007000" // nomor WA tujuan
            val url = "https://wa.me/$phoneNumber"

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(intent)
        }
    }
}