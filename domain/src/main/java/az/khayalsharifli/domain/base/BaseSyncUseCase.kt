package az.khayalsharifli.domain.base

import az.khayalsharifli.domain.exceptions.ErrorConverter
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

typealias Block<T> = BaseSyncUseCase.Request<T>.() -> Unit

abstract class BaseSyncUseCase<P, R>(
    private val context: CoroutineContext,
    private val errorConverter: ErrorConverter
) {
    protected abstract suspend fun executeOnBackground(params: P): R

    suspend fun execute(params: P, block: Block<R>) {
        val request = Request<R>().apply(block).also { it.onStart!!.invoke() }
        try {
            val result = withContext(context = context) { executeOnBackground(params) }
            request.onSuccess(result)
        } catch (e: CancellationException) {
            request.onCancel?.invoke(e)
        } catch (e: Throwable) {
            request.onError?.invoke(errorConverter.convert(e))
        } finally {
            request.onTerminate?.invoke()
        }
    }


    class Request<T> {
        var onSuccess: (T) -> Unit = {}
        var onStart: (() -> Unit)? = null
        var onError: ((Throwable) -> Unit)? = null
        var onCancel: ((CancellationException) -> Unit)? = null
        var onTerminate: (() -> Unit)? = null
    }
}