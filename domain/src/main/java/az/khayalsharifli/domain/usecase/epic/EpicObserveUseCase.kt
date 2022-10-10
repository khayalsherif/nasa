package az.khayalsharifli.domain.usecase.epic

import az.khayalsharifli.domain.base.BaseObserveUseCase
import az.khayalsharifli.domain.exceptions.ErrorConverter
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.repository.EpicRepository
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class EpicObserveUseCase(
    context: CoroutineContext,
    converter: ErrorConverter,
    private val repository: EpicRepository
) : BaseObserveUseCase<Unit, List<Epic>>(context, converter) {

    override fun createFlow(params: Unit): Flow<List<Epic>> {
        return repository.observeEpics()
    }

}