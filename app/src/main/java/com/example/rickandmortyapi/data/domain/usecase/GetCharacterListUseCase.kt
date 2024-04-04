package com.example.rickandmortyapi.data.domain.usecase

import com.example.rickandmortyapi.data.domain.model.character.CharacterListModel
import com.example.rickandmortyapi.data.domain.repository.DataProvider
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetCharacterListUseCase(private val dataProvider: DataProvider) {
    operator fun invoke(page: Int): Flow<BaseResponse<CharacterListModel>> {
        return dataProvider.getCharacterList(page)
    }
}