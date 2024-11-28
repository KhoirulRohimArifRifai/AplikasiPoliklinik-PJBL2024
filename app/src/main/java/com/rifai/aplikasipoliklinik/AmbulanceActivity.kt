package com.rifai.aplikasipoliklinik

import android.content.Intent
import android.os.Bundle
import android.net.Uri
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AmbulanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ambulance)

        val kembali: ImageView = findViewById(R.id.kembali)
        kembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val hubAmbulance: CardView = findViewById(R.id.hubambulance)
        hubAmbulance.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:02712936000")
            startActivity(intent)
        }
    }
}