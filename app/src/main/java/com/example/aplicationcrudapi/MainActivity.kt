package com.example.aplicationcrudapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.aplicationcrudapi.adapter.MahasiswaAdapter
import com.example.aplicationcrudapi.adapter.OnitemClickListener
import com.example.aplicationcrudapi.model.action.ResponseData
import com.example.aplicationcrudapi.model.data.ResponseMahasiwa
import com.example.aplicationcrudapi.model.data.ResultItem
import com.example.aplicationcrudapi.network.ApiNetwork
import com.example.aplicationcrudapi.network.Config
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showData()
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun showData() {
        ApiNetwork.getMahasiswa().getDataMahasiswa().enqueue(object : Callback<ResponseMahasiwa> {
            override fun onFailure(call: Call<ResponseMahasiwa>, t: Throwable) {
                Log.e("Error Server", t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseMahasiwa>,
                response: Response<ResponseMahasiwa>
            ) {
                Log.d("Server Respawn Ok", response.message())
                val data = response.body()?.result
                listItem.adapter = MahasiswaAdapter(data, object : OnitemClickListener {
                    override fun hapus(item: ResultItem?) {
                        AlertDialog.Builder(this@MainActivity).apply {
                            setTitle("Delete Data")
                            setMessage("Are You Sure?")
                            setPositiveButton("Delete"){dialog,which->
                                hapusData(item?.npm)
                                dialog.dismiss()
                            }
                            setNegativeButton("Cancel"){dialog,which->
                                dialog.dismiss()
                            }
                        }.show()
                    }

                    override fun update(item: ResultItem?) {
                        val intent = Intent(this@MainActivity,InputMahasiswaActivity::class.java)
                        intent.putExtra(EXTRA_DATA_MAHASISWA,item)
                        startActivity(intent)
                    }

                })
            }
        })
    }

    fun hapusData(npm: String?) {
        val hapus =
            ApiNetwork.getMahasiswa().deleteData(npm).enqueue(object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("error server", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        showData()
                    }
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.menu_add -> {
                    val intent = Intent(this, InputMahasiswaActivity::class.java)
                    startActivity(intent)

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
    companion object{
        const val EXTRA_DATA_MAHASISWA = "extra_data_mahasiswa"
    }
}