package com.example.rickandmortyapi.data.domain.repository.remote.mapper.character

import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.character.CharacterResponse

class CharacterMapper : ResponseMapper<CharacterResponse, CharacterModel> {
    override fun fromResponse(response: CharacterResponse): CharacterModel {

        val type = if (!response.type.isNullOrEmpty()) {
            response.type
        } else {
            "No type"
        }

        return CharacterModel(
            response.episode ?: listOf(),
            response.gender ?: "",
            response.id ?: -1,
            response.image ?: "",
            response.name ?: "",
            response.species ?: "",
            type,
            response.status ?: "",
            response.url ?: ""
        )
    }
}
