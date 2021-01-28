package com.example.aplicationcrudapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicationcrudapi.R
import com.example.aplicationcrudapi.model.data.ResultItem
import kotlinx.android.synthetic.main.listdata_item.view.*

class MahasiswaAdapter(val data: ArrayList<ResultItem?>?,val itemClick:OnitemClickListener):RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val npm = view.txtNpm
        val nama = view.txtNama
        val jurusan = view.txtJurusan
        val angkatan = view.txtAngkatan
        val btnhapus = view.btnHapus
        val btnUpdate = view.btnUpdate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.listdata_item,parent,false)
        val holder = ViewHolder(layout)
        return holder
    }

    override fun getItemCount(): Int = data?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.npm.text = data?.get(position)?.npm
        holder.nama.text = data?.get(position)?.nama
        holder.jurusan.text = data?.get(position)?.jurusan
        holder.angkatan.text = data?.get(position)?.angkatan
        holder.btnhapus.setOnClickListener {
            itemClick.hapus(item)
        }
        holder.btnUpdate.setOnClickListener {
            itemClick.update(item)
        }
    }
}
interface OnitemClickListener{
    fun hapus(item: ResultItem?)
    fun update(item: ResultItem?)
}