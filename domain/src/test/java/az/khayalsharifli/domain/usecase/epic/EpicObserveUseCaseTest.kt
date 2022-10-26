package az.khayalsharifli.domain.usecase.epic

import az.khayalsharifli.domain.exceptions.ErrorConverter
import az.khayalsharifli.domain.factory.epic.EpicFactory
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.repository.EpicRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.runBlocking
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

    private lateinit var epicObserveUseCase: EpicObserveUseCase

    @Before
    fun setUp() {
        epicObserveUseCase =
            EpicObserveUseCase(context, convertor, repository)
    }

    @Test
    fun `observe data from repository`() = runBlocking {
        //Given
        val epicDomainList = EpicFactory.generateDomainEpicList(4)
        val flow = flow { emit(epicDomainList) }

        //When
        stubRepositoryObserveEpic(flow)

        //Then
        Truth.assertThat(repository.observeEpics()).isEqualTo(flow)
    }

    private fun stubRepositoryObserveEpic(flow: Flow<List<Epic>>) {
        `when`(repository.observeEpics())
            .thenReturn(flow)
    }
}