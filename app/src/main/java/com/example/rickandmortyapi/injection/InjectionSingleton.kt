package com.example.rickandmortyapi.injection

import com.example.rickandmortyapi.data.domain.repository.DataProvider
import com.example.rickandmortyapi.data.domain.repository.remote.ApiCallService
import com.example.rickandmortyapi.data.domain.repository.remote.ApiService
import com.example.rickandmortyapi.data.domain.repository.remote.RemoteDataSource
import com.example.rickandmortyapi.data.domain.repository.remote.RetrofitClient

class InjectionSingleton {
    companion object {
        private fun provideApiServices(): ApiService {
            return RetrofitClient.getInstance().getApiServices()
        }

        private fun provideCallApiService(): ApiCallService {
            return ApiCallService(provideApiServices())
        }

        fun provideDataSource(): DataProvider {
            return DataProvider.getInstance(RemoteDataSource.getInstance(provideCallApiService()))
        }
    }
}