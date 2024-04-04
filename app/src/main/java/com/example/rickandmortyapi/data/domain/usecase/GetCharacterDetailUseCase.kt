package com.example.rickandmortyapi.data.domain.usecase

import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.repository.DataProvider
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetCharacterDetailUseCase(private val dataProvider: DataProvider) {
    operator fun invoke(character: String): Flow<BaseResponse<CharacterModel>> {
        return dataProvider.getCharacterDetail(character)
    }
}