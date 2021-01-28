package com.example.aplicationcrudapi.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiNetwork {
    companion object {
        fun interceptor(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return client
        }
        fun getMahasiswa(): Config{
            val mahasiswaRetrofit = Retrofit.Builder()
                .baseUrl("your_ip/crud_mahasiswa/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptor())
                .build()
            val serviceMahasiswa = mahasiswaRetrofit.create(Config::class.java)
            return serviceMahasiswa
        }
    }
}