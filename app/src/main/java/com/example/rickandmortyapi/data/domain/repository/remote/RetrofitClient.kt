package com.example.rickandmortyapi.data.domain.repository.remote

import com.example.rickandmortyapi.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


class RetrofitClient() {

    companion object {
        private var INSTANCE: RetrofitClient? = null

        @Synchronized
        fun getInstance(): RetrofitClient {
            if (INSTANCE == null) {
                INSTANCE = RetrofitClient()
            }
            return INSTANCE!!
        }
    }

    private val retrofit: Retrofit
    private val apiService: ApiService

    init {
        val gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getApiServices(): ApiService {
        return apiService
    }
}