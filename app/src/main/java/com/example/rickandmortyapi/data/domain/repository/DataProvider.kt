package com.example.rickandmortyapi.data.domain.repository

import com.example.rickandmortyapi.data.domain.model.character.CharacterListModel
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class DataProvider(private val dataSource: DataSource) : DataSource {

    companion object {
        private var INSTANCE: DataProvider? = null

        @Synchronized
        fun getInstance(remoteDataSource: DataSource): DataProvider {
            if (INSTANCE == null) {
                INSTANCE = DataProvider(remoteDataSource)
            }
            return INSTANCE!!
        }
    }

    override fun getCharacterList(page: Int): Flow<BaseResponse<CharacterListModel>> {
        return dataSource.getCharacterList(page)
    }

    override fun getCharacterDetail(url: String): Flow<BaseResponse<CharacterModel>> {
        return dataSource.getCharacterDetail(url)
    }

}