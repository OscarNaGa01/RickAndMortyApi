package com.example.rickandmortyapi.data.domain.repository

import com.example.rickandmortyapi.data.domain.model.character.CharacterListModel
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getCharacterList(page: Int): Flow<BaseResponse<CharacterListModel>>

    fun getCharacterDetail(url: String): Flow<BaseResponse<CharacterModel>>
}