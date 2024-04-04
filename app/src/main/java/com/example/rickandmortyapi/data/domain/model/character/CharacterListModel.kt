package com.example.rickandmortyapi.data.domain.model.character

import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterListModel(
    val info: InfoModel = InfoModel(),
    val results: ArrayList<CharacterModel> = arrayListOf()
) : BaseModel()