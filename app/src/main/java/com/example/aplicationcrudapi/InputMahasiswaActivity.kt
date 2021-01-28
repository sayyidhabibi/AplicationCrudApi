package com.example.aplicationcrudapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.aplicationcrudapi.model.action.ResponseData
import com.example.aplicationcrudapi.model.data.ResultItem
import com.example.aplicationcrudapi.network.ApiNetwork
import kotlinx.android.synthetic.main.activity_input_mahasiswa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InputMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_mahasiswa)
        val dataitem = intent.getParcelableExtra<ResultItem>(MainActivity.EXTRA_DATA_MAHASISWA)
        if (dataitem != null) {
            btnSimpan.text = "Update"
            edtNpm.setText(dataitem.npm)
            edtNama.setText(dataitem.nama)
            edtJurusan.setText(dataitem.jurusan)
            edtAngkatan.setText(dataitem.angkatan)
        } else {
            btnSimpan.text = "Simpan"
        }
        when (btnSimpan.text) {
            "Simpan" -> {
                btnSimpan.setOnClickListener {
                    val npm = edtNpm.text.toString()
                    val nama = edtNama.text.toString()
                    val jurusan = edtJurusan.text.toString()
                    val angkatan = edtAngkatan.text.toString()
                    addMahasiswa(npm, nama, jurusan, angkatan)
                }
            }
            "Update" -> {
                btnSimpan.setOnClickListener {
                    val npm = dataitem?.npm ?:""
                    val nama = edtNama.text.toString()
                    val jurusan = edtJurusan.text.toString()
                    val angkatan = edtAngkatan.text.toString()
                    updateMahasiswa(npm, nama, jurusan, angkatan)
                }

            }
        }


    }
//function tambahdata
    private fun addMahasiswa(npm: String, nama: String, jurusan: String, angkatan: String) {
        val input = ApiNetwork.getMahasiswa().insertDataMahasiswa(npm, nama, jurusan, angkatan)
        input.enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("Erorr Respawn", t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseData>,
                response: Response<ResponseData>
            ) {
                if (response.isSuccessful) {
                    val message = response.body()?.message
                    Toast.makeText(this@InputMahasiswaActivity, message, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val message = response.body()?.message
                    Toast.makeText(this@InputMahasiswaActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    //function update
    private fun updateMahasiswa(npm: String, nama: String, jurusan: String, angkatan: String) {
        val update = ApiNetwork.getMahasiswa().updateData(npm, nama, jurusan, angkatan)
            update.enqueue(object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("Error Server", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        Toast.makeText(this@InputMahasiswaActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val message = response.body()?.message
                        Toast.makeText(this@InputMahasiswaActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            })
    }
}
