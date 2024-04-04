package com.example.rickandmortyapi.data.domain.model.character

import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val episode: List<String> = listOf(),
    val gender: String = "",
    val id: Int = -1,
    val image: String = "",
    val name: String = "",
    val species: String = "",
    val type: String = "",
    val status: String = "",
    val url: String = ""
) : BaseModel()