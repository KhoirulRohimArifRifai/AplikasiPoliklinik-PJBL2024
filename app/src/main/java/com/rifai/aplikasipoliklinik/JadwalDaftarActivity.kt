package com.rifai.aplikasipoliklinik

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JadwalDaftarActivity : AppCompatActivity() {
    lateinit var back: ImageView

    private val database = FirebaseDatabase.getInstance("https://aplikasipoliklinik-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val myRef = database.getReference("Pendaftaran")
    lateinit var jadwallist:ArrayList<User>

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jadwal_daftar)
        FirebaseApp.initializeApp(this)
        recyclerView = findViewById(R.id.itemlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        jadwallist = arrayListOf<User>()
        getData()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getData() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    jadwallist.clear()
                    for (dataSnapshot in snapshot.children) {
                        val jadwal = dataSnapshot.getValue(User::class.java)
                        jadwallist.add(jadwal!!)
                    }
                    val adapter = Adapter(jadwallist)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@JadwalDaftarActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}