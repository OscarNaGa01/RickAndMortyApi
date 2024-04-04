package com.example.rickandmortyapi.data.domain.repository.remote.mapper.character

interface ResponseMapper<T, U> {
    fun fromResponse(response: T): U
}
