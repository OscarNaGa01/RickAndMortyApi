package com.example.rickandmortyapi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.model.error.ErrorModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import com.example.rickandmortyapi.data.domain.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val getCharacterDetailUseCase: GetCharacterDetailUseCase) : ViewModel() {

    private val characterDetailMutableStateFlow = MutableStateFlow(CharacterModel())
    val characterDetailStateFlow: StateFlow<CharacterModel> = characterDetailMutableStateFlow

    private val characterErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val characterErrorSharedFlow: SharedFlow<ErrorModel> = characterErrorMutableSharedFlow

    fun getCharacterDetail(character: String?) {
        character?.let {
            viewModelScope.launch(Dispatchers.IO) {
                getCharacterDetailUseCase(character).collect {
                    when (it) {
                        is BaseResponse.Error -> {
                            characterErrorMutableSharedFlow.emit(it.error)
                        }

                        is BaseResponse.Success -> {
                            characterDetailMutableStateFlow.value = it.data
                        }
                    }
                }
            }
        }
    }
}