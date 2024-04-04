package com.example.rickandmortyapi.data.domain.repository.remote.mapper.error

import com.example.rickandmortyapi.data.domain.model.error.ErrorModel
import com.example.rickandmortyapi.data.domain.repository.remote.mapper.character.ResponseMapper
import com.example.rickandmortyapi.data.domain.repository.remote.response.error.ErrorResponse

class ErrorMapper : ResponseMapper<ErrorResponse, ErrorModel> {
    override fun fromResponse(response: ErrorResponse): ErrorModel {
        return ErrorModel(response.error ?: "", response.errorCode ?: "", response.message ?: "")
    }
}