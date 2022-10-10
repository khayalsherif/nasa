package az.khayalsharifli.domain.usecase.splash

import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

class SplashUseCase(context: CoroutineContext) {
    suspend fun executeBackground() {
        delay(2000)
    }
}