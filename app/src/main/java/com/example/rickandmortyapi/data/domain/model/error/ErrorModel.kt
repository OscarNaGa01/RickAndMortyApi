package com.example.rickandmortyapi.data.domain.model.error

import com.example.rickandmortyapi.data.domain.model.character.BaseModel

class ErrorModel(
    var error: String = "unknow",
    var errorCode: String = "",
    var message: String = "unknow"
) : BaseModel()