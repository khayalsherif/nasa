package az.khayalsharifli.domain.usecase.epic

import az.khayalsharifli.domain.base.BaseSyncUseCase
import az.khayalsharifli.domain.exceptions.ErrorConverter
import az.khayalsharifli.domain.repository.EpicRepository
import kotlin.coroutines.CoroutineContext

class EpicSyncUseCase(
    context: CoroutineContext,
    converter: ErrorConverter,
    private val repository: EpicRepository
) : BaseSyncUseCase<Unit, Unit>(context, converter) {

    override suspend fun executeOnBackground(params: Unit) {
        repository.sync()
    }
}