package com.rifai.aplikasipoliklinik

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Adapter
    (private val listHasil:ArrayList<User>) : RecyclerView.Adapter<Adapter.MyViewHolder>(){
    lateinit var appContext: Context
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nama: TextView = itemView.findViewById(R.id.tvnama)
        val umur: TextView = itemView.findViewById(R.id.tvumur)
        val kelamin: TextView = itemView.findViewById(R.id.tvkelamin)
        val alamat: TextView = itemView.findViewById(R.id.tvalamat)
        val nohp: TextView = itemView.findViewById(R.id.tvno)
        val tgldaftar: TextView = itemView.findViewById(R.id.tvdaftar)
        val pilihanpoli : TextView = itemView.findViewById(R.id.tvpoli)
        val hapusButton: Button = itemView.findViewById(R.id.hapus) // Button hapus
        val editButton: Button = itemView.findViewById(R.id.edit) // Button edit

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        appContext = parent.context
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listHasil[position]
        holder.nama.text = item.nama
        holder.umur.text = item.umur
        holder.kelamin.text = item.jk
        holder.alamat.text = item.alamat
        holder.nohp.text = item.noHp
        holder.tgldaftar.text = item.tglDaftar
        holder.pilihanpoli.text = item.pilihanPoli

        val database = FirebaseDatabase.getInstance("https://aplikasipoliklinik-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val myRef = database.getReference("Pendaftaran")

        // Set OnClickListener untuk edit button
        holder.editButton.setOnClickListener {
            // Membuat dialog edit
            val dialogView = LayoutInflater.from(appContext).inflate(R.layout.dialog_edit, null)
            val builder = AlertDialog.Builder(appContext).setView(dialogView).setTitle("Edit Data")

            // Mengisi EditText dengan data yang sudah ada
            val editNama = dialogView.findViewById<EditText>(R.id.editnama)
            val editUmur = dialogView.findViewById<EditText>(R.id.editumur)
            val editKelamin = dialogView.findViewById<Spinner>(R.id.editjk)
            val editAlamat = dialogView.findViewById<EditText>(R.id.editalamat)
            val editNoHp = dialogView.findViewById<EditText>(R.id.editno_hp)
            val editTglDaftar = dialogView.findViewById<EditText>(R.id.edittgl_daftar)
            val editPilihanPoli = dialogView.findViewById<Spinner>(R.id.editpilihanpoli)

            editNama.setText(item.nama)
            editUmur.setText(item.umur)
            editKelamin.setSelection(appContext.resources.getStringArray(R.array.jenis).indexOf(item.jk))
            editAlamat.setText(item.alamat)
            editNoHp.setText(item.noHp)
            editTglDaftar.setText(item.tglDaftar)
            editPilihanPoli.setSelection(appContext.resources.getStringArray(R.array.poli).indexOf(item.pilihanPoli))

            builder.setPositiveButton("Simpan") { dialog, _ ->
                // Update data di Firebase
                val updatedData = User(
                    nama = editNama.text.toString(),
                    umur = editUmur.text.toString(),
                    jk = editKelamin.selectedItem.toString(),
                    alamat = editAlamat.text.toString(),
                    noHp = editNoHp.text.toString(),
                    tglDaftar = editTglDaftar.text.toString(),
                    pilihanPoli = editPilihanPoli.selectedItem.toString()
                )

                myRef.orderByChild("nama").equalTo(item.nama).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val snapshot = task.result
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                dataSnapshot.ref.setValue(updatedData) // Update data di Firebase
                            }
                            // Update data di RecyclerView
                            listHasil[position] = updatedData
                            notifyItemChanged(position)
                            Toast.makeText(appContext, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(appContext, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(appContext, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()
            }

            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss() // Tutup dialog tanpa melakukan apapun
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        // Set OnClickListener untuk hapus button
        holder.hapusButton.setOnClickListener {
            // Membuat dialog konfirmasi
            val builder = AlertDialog.Builder(appContext)
            builder.setTitle("Konfirmasi Hapus")
            builder.setMessage("Apakah Anda yakin ingin menghapus data ini?")

            // Jika pengguna memilih "Ya", lanjutkan penghapusan data
            builder.setPositiveButton("Ya") { dialog, _ ->
                myRef.orderByChild("nama").equalTo(item.nama).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val snapshot = task.result
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                dataSnapshot.ref.removeValue() // Hapus data dari Firebase
                            }
                            // Hapus item dari list dan notify perubahan
                            listHasil.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, listHasil.size)
                            Toast.makeText(appContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(appContext, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(appContext, "Gagal menghapus data", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss() // Tutup dialog setelah penghapusan
            }

            // Jika pengguna memilih "Tidak", tutup dialog
            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Tutup dialog tanpa melakukan apapun
            }

            // Tampilkan dialog konfirmasi
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
    override fun getItemCount(): Int {
        return listHasil.size
    }
}