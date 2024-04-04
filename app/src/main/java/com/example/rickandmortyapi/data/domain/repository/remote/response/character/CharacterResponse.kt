package com.example.rickandmortyapi.data.domain.repository.remote.response.character

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: List<String>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: CharacterLocationResponse?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: CharacterOriginResponse?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)