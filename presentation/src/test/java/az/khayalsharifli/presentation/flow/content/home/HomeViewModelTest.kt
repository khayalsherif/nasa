package az.khayalsharifli.presentation.flow.content.home

import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.usecase.epic.EpicObserveUseCase
import az.khayalsharifli.domain.usecase.epic.EpicSyncUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var epicObserveUseCase: EpicObserveUseCase

    @Mock
    private lateinit var epicSyncUseCase: EpicSyncUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(epicObserveUseCase, epicSyncUseCase)
    }

    @Test
    fun getDataFromViewModel() = runBlocking {

    }

    fun stubHomeViewModelGetEpicData(job: Job) {
        Mockito.`when`(homeViewModel.getEpicData()).thenReturn(job)
    }

    fun stubHomeViewModelEpicResponse(flow: StateFlow<List<Epic>>) {
        Mockito.`when`(homeViewModel.epicResponse).thenReturn(flow)
    }
}