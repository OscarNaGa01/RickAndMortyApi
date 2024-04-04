package com.example.rickandmortyapi.data.domain.model.character

import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoModel(
    val count: Int = -1,
    val next: String = "",
    val pages: Int = -1,
    val prev: String = ""
) : BaseModel()