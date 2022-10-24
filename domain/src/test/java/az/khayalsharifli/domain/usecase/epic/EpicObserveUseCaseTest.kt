package az.khayalsharifli.domain.usecase.epic

import az.khayalsharifli.domain.exceptions.ErrorConverter
import az.khayalsharifli.domain.factory.epic.EpicFactory
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.repository.EpicRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.coroutines.CoroutineContext

@RunWith(MockitoJUnitRunner::class)
class EpicObserveUseCaseTest {


    @Mock
    lateinit var context: CoroutineContext

    @Mock
    lateinit var convertor: ErrorConverter

    @Mock
    lateinit var repository: EpicRepository

    lateinit var epicObserveUseCase: EpicObserveUseCase


    @Before
    fun setUp() {
        epicObserveUseCase =
            EpicObserveUseCase(context, convertor, repository)
    }


    @Test
    fun useCaseObserver_callRepository() {
        runBlocking {
            val mockData = EpicFactory.generateDummyEpicList(1)
            val flow = MutableStateFlow(mockData)
            epicObserveRepositoryGetList(flow)

            val data = epicObserveUseCase.execute(Unit).first()

            assertThat(data).isEqualTo(mockData)
        }

    }

    private fun epicObserveRepositoryGetList(flow: Flow<List<Epic>>) {
        `when`(epicObserveUseCase.execute(Unit))
            .thenReturn(flow)
    }
}