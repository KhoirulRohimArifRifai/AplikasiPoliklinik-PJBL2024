package com.rifai.aplikasipoliklinik

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DokterActivity : AppCompatActivity() {
    lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dokter)

        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val umumCard: CardView = findViewById(R.id.umum)
        umumCard.setOnClickListener {
            val intent = Intent(this, DokterUmumActivity::class.java)
            startActivity(intent)
        }
        val gigiCard: CardView = findViewById(R.id.gigi)
        gigiCard.setOnClickListener {
            val intent = Intent(this, DokterGigiActivity::class.java)
            startActivity(intent)
        }
    }
}