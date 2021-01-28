package com.example.aplicationcrudapi.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseMahasiwa(

	@field:SerializedName("result")
	val result: ArrayList<ResultItem?>? = null
)
@Parcelize
data class ResultItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("angkatan")
	val angkatan: String? = null,

	@field:SerializedName("npm")
	val npm: String? = null,

	@field:SerializedName("jurusan")
	val jurusan: String? = null
):Parcelable
