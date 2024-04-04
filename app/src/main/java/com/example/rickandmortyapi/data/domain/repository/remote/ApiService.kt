package com.example.rickandmortyapi.data.domain.repository.remote

import com.example.rickandmortyapi.data.domain.repository.remote.response.character.CharacterListResponse
import com.example.rickandmortyapi.data.domain.repository.remote.response.character.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getCharacterList(
        @Url url: String,
        @Query("page") page: Int
    ): Response<CharacterListResponse>

    @GET
    suspend fun getCharacterDetail(
        @Url url: String
    ): Response<CharacterResponse>
}