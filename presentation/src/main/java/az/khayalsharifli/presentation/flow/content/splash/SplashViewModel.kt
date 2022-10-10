package az.khayalsharifli.presentation.flow.content.splash

import androidx.lifecycle.viewModelScope
import az.khayalsharifli.domain.usecase.splash.SplashUseCase
import az.khayalsharifli.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(splashUseCase: SplashUseCase) : BaseViewModel() {

    private var _splashState = MutableStateFlow(false)
    val splashState: StateFlow<Boolean> get() = _splashState.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                splashUseCase.executeBackground()
                _splashState.emit(true)
            }
        }
    }

}
