package com.example.aplicationcrudapi.network

import com.example.aplicationcrudapi.model.action.ResponseData
import com.example.aplicationcrudapi.model.data.ResponseMahasiwa
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Config {
    //Insert Data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertDataMahasiswa(
        @Field("npm") npm: String?,
        @Field("nama") nama: String?,
        @Field("jurusan") jurusan: String?,
        @Field("angkatan") angkatan: String?
    ):Call<ResponseData>
    // Get Data
    @GET("read.php")
    fun getDataMahasiswa():Call<ResponseMahasiwa>
    //Delete Data
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(
        @Field("npm") npm:String?
    ):Call<ResponseData>
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(
        @Field("npm")npm:String?,
        @Field("nama")nama: String?,
        @Field("jurusan")jurusan: String?,
        @Field("angkatan")angkatan: String?
    ):Call<ResponseData>
}