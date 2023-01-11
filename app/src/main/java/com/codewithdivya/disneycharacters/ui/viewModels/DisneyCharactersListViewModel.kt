package com.codewithdivya.disneycharacters.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdivya.disneycharacters.business.usecase.DisneyCharactersListUseCase
import com.codewithdivya.disneycharacters.utils.Result
import com.codewithdivya.disneycharacters.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisneyCharactersListViewModel @Inject constructor(
    private val useCase: DisneyCharactersListUseCase,
    private var dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _stateFlow

    fun getDisneyCharactersList() {
        _stateFlow.value = UIState.Loading
        viewModelScope.launch(dispatcher) {
            useCase.invoke()
                .collect { resultState ->
                    println("Coroutines result collected in ${Thread.currentThread().name}")
                    _stateFlow.value = when (resultState) {
                        is Result.Success -> UIState.Success(resultState.data)
                        is Result.Failure -> UIState.Error(resultState.throwable)
                    }
                }
        }
    }
}

