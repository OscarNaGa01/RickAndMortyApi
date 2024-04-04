package com.example.rickandmortyapi.data.domain.repository.remote.response.character

import com.google.gson.annotations.SerializedName

data class CharacterOriginResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)