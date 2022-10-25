package az.khayalsharifli.presentation.flow.content.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import az.khayalsharifli.domain.exceptions.ErrorConverter
import az.khayalsharifli.domain.repository.EpicRepository
import az.khayalsharifli.domain.usecase.epic.EpicObserveUseCase
import az.khayalsharifli.domain.usecase.epic.EpicSyncUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var epicObserveUseCase: EpicObserveUseCase

    private lateinit var epicSyncUseCase: EpicSyncUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var mockContext: CoroutineContext

    @Mock
    private lateinit var convertor: ErrorConverter

    @Mock
    private lateinit var repository: EpicRepository

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        setUpUseCase()
        homeViewModel = HomeViewModel(epicObserveUseCase, epicSyncUseCase)
    }

    private fun setUpUseCase() {
        epicObserveUseCase = EpicObserveUseCase(mockContext, convertor, repository)
        epicSyncUseCase = EpicSyncUseCase(mockContext, convertor, repository)
    }


    @Test
    fun getDataFromViewModel() {
        runBlocking {
            homeViewModel.getEpicData()

            val first = homeViewModel.epicResponse.first()
            assertEquals(first.size, 1)
        }

    }
}