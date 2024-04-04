package com.example.rickandmortyapi.data.domain.repository.remote.response.character

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("info")
    val info: InfoResponse?,
    @SerializedName("results")
    val results: List<CharacterResponse>?
)