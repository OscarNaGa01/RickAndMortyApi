package com.example.rickandmortyapi.data.domain.repository.remote

import com.example.rickandmortyapi.data.domain.model.character.CharacterListModel
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.repository.DataSource
import com.example.rickandmortyapi.data.domain.repository.remote.mapper.character.CharacterListMapper
import com.example.rickandmortyapi.data.domain.repository.remote.mapper.character.CharacterMapper
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiCallService: ApiCallService) : DataSource {

    companion object {
        private var INSTANCE: RemoteDataSource? = null

        @Synchronized
        fun getInstance(apiCallService: ApiCallService): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(apiCallService)
            }
            return INSTANCE!!
        }
    }

    override fun getCharacterList(page: Int): Flow<BaseResponse<CharacterListModel>> =
        flow {
            val apiResult = apiCallService.getCharacterList(page)
            if (apiResult is BaseResponse.Success) {
                emit(BaseResponse.Success(CharacterListMapper().fromResponse(apiResult.data)))
            } else if (apiResult is BaseResponse.Error) {
                emit(BaseResponse.Error(apiResult.error))
            }
        }

    override fun getCharacterDetail(url: String): Flow<BaseResponse<CharacterModel>> =
        flow {
            val apiResult = apiCallService.getCharacterDetail(url)
            if (apiResult is BaseResponse.Success) {
                emit(BaseResponse.Success(CharacterMapper().fromResponse(apiResult.data)))
            } else if (apiResult is BaseResponse.Error) {
                emit(BaseResponse.Error(apiResult.error))
            }
        }
}