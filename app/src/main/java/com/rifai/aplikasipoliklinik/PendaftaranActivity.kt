package com.rifai.aplikasipoliklinik

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class PendaftaranActivity : AppCompatActivity() {
    val database =
        FirebaseDatabase.getInstance("https://aplikasipoliklinik-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("Pendaftaran")
    lateinit var back: ImageView
    lateinit var nama: EditText
    lateinit var umur: EditText
    lateinit var jeniskelamin: Spinner
    lateinit var alamat: EditText
    lateinit var no_hp: EditText
    lateinit var tgl_daftar: EditText
    lateinit var pilihanpoli: Spinner
    lateinit var btdaftar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pendaftaran)

        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi EditText dan Button
        val nama = findViewById<EditText>(R.id.nama)
        val umur = findViewById<EditText>(R.id.umur)
        val jenisKelamin = findViewById<Spinner>(R.id.jk)
        val alamat = findViewById<EditText>(R.id.alamat)
        val noHp = findViewById<EditText>(R.id.no_hp)
        val pilihanPoli = findViewById<Spinner>(R.id.pilihanpoli)
        val btnDaftar = findViewById<Button>(R.id.daftar)
        val tglDaftar = findViewById<EditText>(R.id.tgl_daftar)


        btnDaftar.setOnClickListener {
            // Validasi Form
            if (validateForm(nama, umur, jenisKelamin, alamat, noHp, tglDaftar, pilihanPoli)) {
                // Membuat objek User
                val user = User(
                    nama.text.toString().trim(),
                    umur.text.toString().trim(),
                    jenisKelamin.selectedItem.toString().trim(),
                    alamat.text.toString().trim(),
                    noHp.text.toString().trim(),
                    tglDaftar.text.toString().trim(),
                    pilihanPoli.selectedItem.toString()
                )

                // Menyimpan data ke Firebase
                myRef.push().setValue(user)
                    .addOnSuccessListener {
                        // Tampilkan Toast jika berhasil
                        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()

                        // Kosongkan semua EditText
                        nama.setText("")
                        umur.setText("")
                        jenisKelamin.setSelection(0)
                        alamat.setText("")
                        noHp.setText("")
                        tglDaftar.setText("")
                        pilihanPoli.setSelection(0)

                        // Pindah ke activity_jadwal_daftar
                        val intent = Intent(this, JadwalDaftarActivity::class.java)
                        startActivity(intent)
                        finish() // Menutup aktivitas saat ini agar tidak kembali dengan tombol Back
                    }
                    .addOnFailureListener {
                        // Tampilkan Toast jika gagal
                        Toast.makeText(
                            this,
                            "Terjadi kesalahan saat menyimpan data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
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

    // Fungsi validasi form
    private fun validateForm(
        nama: EditText,
        umur: EditText,
        jenisKelamin: Spinner,
        alamat: EditText,
        noHp: EditText,
        tglDaftar: EditText,
        pilihanPoli: Spinner
    ): Boolean {
        var valid = true

        // Validasi Nama
        val namaInput = nama.text.toString().trim()
        if (namaInput.isEmpty()) {
            nama.error = "Nama tidak boleh kosong"
            valid = false
        } else if (!namaInput.matches(Regex("^[a-zA-Z\\s]+$"))) {
            nama.error = "Nama hanya boleh berisi huruf"
            valid = false
        } else {
            nama.error = null
        }


        // Validasi Tanggal Lahir
        if (umur.text.toString().trim().isEmpty()) {
            umur.error = "Tanggal lahir tidak boleh kosong"
            valid = false
        } else {
            umur.error = null
        }

        // Validasi Jenis Kelamin
        val selectedJenisKelamin = jenisKelamin.selectedItem.toString().trim()
        if (selectedJenisKelamin.isEmpty() || (selectedJenisKelamin != "Laki - Laki" && selectedJenisKelamin != "Perempuan")) {
            val toast = Toast.makeText(this, "Pilih jenis kelamin yang valid", Toast.LENGTH_SHORT)
            toast.show()

            // Durasi kustom untuk Toast, misalnya 3 detik
            Handler(Looper.getMainLooper()).postDelayed({
                toast.cancel()  // Membatalkan Toast setelah 3 detik
            }, 3500) //

            valid = false
        }


        // Validasi Alamat
        if (alamat.text.toString().trim().isEmpty()) {
            alamat.error = "Alamat tidak boleh kosong"
            valid = false
        } else {
            alamat.error = null
        }

        // Validasi Nomor Telepon
        val phone = noHp.text.toString().trim()
        if (phone.isEmpty()) {
            noHp.error = "Nomor telepon tidak boleh kosong"
            valid = false
        } else if (phone.length < 10) {  // Misal minimal panjang nomor telepon adalah 10
            noHp.error = "Nomor telepon minimal 10 digit"
            valid = false
        } else {
            noHp.error = null
        }

        // Validasi Tanggal Daftar
        if (tglDaftar.text.toString().trim().isEmpty()) {
            tglDaftar.error = "Tanggal daftar tidak boleh kosong"
            valid = false
        }else {
            tglDaftar.error = null
        }

        // Validasi Pilihan Poli
        val selectedPoli = pilihanPoli.selectedItem.toString().trim()
        if (selectedPoli.isEmpty() || (selectedPoli != "Poli Umum" && selectedPoli != "Poli Gigi")) {
            val toast = Toast.makeText(this, "Pilih poli yang valid", Toast.LENGTH_SHORT)
            toast.show()

            // Durasi kustom untuk Toast, misalnya 3 detik
            Handler(Looper.getMainLooper()).postDelayed({
                toast.cancel()  // Membatalkan Toast setelah 3 detik
            }, 2500) // 3000 ms = 3 detik

            valid = false
        }

        return valid

    }
    // Data class untuk mewakili data pengguna
    data class User(
        val nama: String,
        val umur: String,
        val jk: String,
        val alamat: String,
        val noHp: String,
        val tglDaftar: String,
        val pilihanPoli: String
    )
}