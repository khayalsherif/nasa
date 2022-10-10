package az.khayalsharifli.domain.base

import az.khayalsharifli.domain.exceptions.ErrorConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class BaseObserveUseCase<P, R>(
    private val context: CoroutineContext,
    private val errorConverter: ErrorConverter
) {
    protected abstract fun createFlow(params: P): Flow<R>

    fun execute(params: P): Flow<R> =
        createFlow(params)
            .flowOn(context)
            .catch { throw errorConverter.convert(it) }
}