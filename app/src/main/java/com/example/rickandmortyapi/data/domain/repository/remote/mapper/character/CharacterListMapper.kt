package com.example.rickandmortyapi.data.domain.repository.remote.mapper.character

import com.example.rickandmortyapi.data.domain.model.character.CharacterListModel
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.model.character.InfoModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.character.CharacterListResponse

class CharacterListMapper : ResponseMapper<CharacterListResponse, CharacterListModel> {
    override fun fromResponse(response: CharacterListResponse): CharacterListModel {
        val resultCharacterModel = arrayListOf<CharacterModel>()
        var info = InfoModel()

        if (!response.results.isNullOrEmpty()) {
            val characterMapper = CharacterMapper()
            response.results.forEach { getCharacterListResultResponse ->
                resultCharacterModel.add(characterMapper.fromResponse(getCharacterListResultResponse))
            }
        }
        if (response.info != null) {
            val characterInfoMapper = CharacterInfoMapper()
            info = characterInfoMapper.fromResponse(response.info)
        }
        return CharacterListModel(info, resultCharacterModel)
    }
}
