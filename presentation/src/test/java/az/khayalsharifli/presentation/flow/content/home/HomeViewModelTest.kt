package az.khayalsharifli.presentation.flow.content.home

import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.usecase.epic.EpicObserveUseCase
import az.khayalsharifli.domain.usecase.epic.EpicSyncUseCase
import az.khayalsharifli.presentation.MainCoroutineRule
import az.khayalsharifli.presentation.factory.epic.EpicFactory
import com.google.common.truth.Truth
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `observe epicResponse data `() = runBlocking {
        //Given
        val epicDomainList = EpicFactory.generateDomainEpicList(4)
        val stateFlow = MutableStateFlow(epicDomainList).asStateFlow()

        //When
        stubHomeViewModelEpicResponse(stateFlow)

        //Then
        Truth.assertThat(homeViewModel.epicResponse.first())
            .isEqualTo(stateFlow.first())
    }

    @Test
    fun `observe empty epicResponse data `() = runBlocking {
        //Given
        val epicDomainList = EpicFactory.generateDomainEpicList(0)
        val stateFlow = MutableStateFlow(epicDomainList).asStateFlow()

        //When
        stubHomeViewModelEpicResponse(stateFlow)

        //Then
        Truth.assertThat(homeViewModel.epicResponse.first())
            .isEqualTo(stateFlow.first())
    }

    private fun stubHomeViewModelEpicResponse(flow: StateFlow<List<Epic>>) {
        Mockito.`when`(homeViewModel.epicResponse).thenReturn(flow)
    }
}