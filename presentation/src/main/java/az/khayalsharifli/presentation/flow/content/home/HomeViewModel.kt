package az.khayalsharifli.presentation.flow.content.home

import androidx.lifecycle.viewModelScope
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.usecase.EpicUseCase
import az.khayalsharifli.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val epicUseCase: EpicUseCase) : BaseViewModel() {

    private var _epicResponse = MutableStateFlow(emptyList<Epic>())
    val epicResponse: StateFlow<List<Epic>>
        get() = _epicResponse.asStateFlow()

    init {
        viewModelScope.launch {
            epicUseCase.getData().collect {
                _epicResponse.emit(it)
            }
        }

        viewModelScope.launch {
            epicUseCase.executeBackground()
        }
    }

}