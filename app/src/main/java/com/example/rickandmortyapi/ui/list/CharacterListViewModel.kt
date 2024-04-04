package com.example.rickandmortyapi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.model.error.ErrorModel
import com.example.rickandmortyapi.data.domain.repository.remote.response.BaseResponse
import com.example.rickandmortyapi.data.domain.usecase.GetCharacterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getCharacterListUseCase: GetCharacterListUseCase) : ViewModel() {

    private val characterListMutableStateFlow = MutableStateFlow<ArrayList<CharacterModel>>(arrayListOf())
    val characterListStateFlow: StateFlow<ArrayList<CharacterModel>> = characterListMutableStateFlow

    private val characterListMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val characterListSharedFlow: SharedFlow<ErrorModel> = characterListMutableSharedFlow

    fun getCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterListUseCase(12).collect {
                when (it) {
                    is BaseResponse.Error -> {
                        characterListMutableSharedFlow.emit(it.error)
                    }

                    is BaseResponse.Success -> {
                        val arrayList = ArrayList(it.data.results)
                        characterListMutableStateFlow.value = arrayList
                    }
                }
            }
        }
    }
}