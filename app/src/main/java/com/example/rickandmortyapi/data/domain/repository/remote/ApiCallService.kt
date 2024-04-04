package com.example.rickandmortyapi.data.domain.repository.remote

import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseApiCallService
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import com.example.rickandmortyapi.data.domain.repository.remote.response.character.CharacterListResponse
import com.example.rickandmortyapi.data.domain.repository.remote.response.character.CharacterResponse

class ApiCallService(private val remoteApiService: ApiService) : BaseApiCallService() {

    suspend fun getCharacterList(page: Int): BaseResponse<CharacterListResponse> {
        return apiCall { remoteApiService.getCharacterList("character", page) }
    }

    suspend fun getCharacterDetail(url: String): BaseResponse<CharacterResponse> {
        return apiCall { remoteApiService.getCharacterDetail(url) }
    }
}