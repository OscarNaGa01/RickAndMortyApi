package com.example.rickandmortyapi.data.domain.repository.remote.mapper.character

import com.example.rickandmortyapi.data.domain.model.character.InfoModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.character.InfoResponse

class CharacterInfoMapper : ResponseMapper<InfoResponse, InfoModel> {
    override fun fromResponse(response: InfoResponse): InfoModel {
        return InfoModel(
            response.count ?: -1,
            response.next ?: "",
            response.pages ?: -1,
            response.prev ?: ""
        )
    }
}