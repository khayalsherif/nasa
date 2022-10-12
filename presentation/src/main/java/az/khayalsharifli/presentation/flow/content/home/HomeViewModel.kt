package az.khayalsharifli.presentation.flow.content.home

import androidx.lifecycle.viewModelScope
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.usecase.epic.EpicObserveUseCase
import az.khayalsharifli.domain.usecase.epic.EpicSyncUseCase
import az.khayalsharifli.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val epicObserveUseCase: EpicObserveUseCase,
    epicSyncUseCase: EpicSyncUseCase
) : BaseViewModel() {

    private var _epicResponse = MutableStateFlow(emptyList<Epic>())
    val epicResponse: StateFlow<List<Epic>>
        get() = _epicResponse.asStateFlow()

    init {
        viewModelScope.launch {
            epicObserveUseCase.execute(Unit).collect {
                _epicResponse.emit(it)
            }
        }
        epicSyncUseCase.launch(Unit)
    }

}