package com.example.appelixirgames.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAppElixir {
    companion object{
        private const val BASE_URL = "https://my-json-server.typicode.com/himuravidal/gamesDB/"

        lateinit var retrofitInstance: Retrofit

        fun getRetrofit(): ApiElixirApp {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiElixirApp::class.java)
        }
    }
}