package az.khayalsharifli.data.repository

import az.khayalsharifli.data.factory.epic.EpicFactory
import az.khayalsharifli.data.local.epic.EpicLocalDataSource
import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.data.mapper.epic.EpicMapper
import az.khayalsharifli.data.remote.EpicService
import az.khayalsharifli.data.remote.model.EpicRemoteDto
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpicRepositoryTest {

    @Mock
    lateinit var mapper: EpicMapper

    @Mock
    lateinit var service: EpicService

    @Mock
    lateinit var epicLocalDataSource: EpicLocalDataSource

    private lateinit var epicRepository: EpicRepositoryImpl

    @Before
    fun setUp() {
        epicRepository = EpicRepositoryImpl(
            mapper = mapper,
            epicService = service,
            epicLocalDataSource = epicLocalDataSource
        )
    }

    @Test
    fun `insert data to local and observe data from local`() = runBlocking {
        //Given
        val epicLocalDtoList = EpicFactory.generateLocalEpicList(4)
        val flow = flow { emit(epicLocalDtoList) }

        //When
        stubEpicLocalDataSourceInsertEpic(epicLocalDtoList, Unit)
        stubEpicLocalDataSourceObserveEpic(flow)
        epicLocalDataSource.insertData(epicLocalDtoList)

        //Then
        Truth.assertThat(
            epicLocalDataSource.observeEpic().first()
        ).isEqualTo(epicLocalDtoList)
    }

    @Test
    fun `insert empty data to local and empty observe data from local`() = runBlocking {
        //Given
        val epicLocalDtoList = EpicFactory.generateLocalEpicList(0)
        val flow = flow { emit(epicLocalDtoList) }

        //When
        stubEpicLocalDataSourceInsertEpic(epicLocalDtoList, Unit)
        stubEpicLocalDataSourceObserveEpic(flow)
        epicLocalDataSource.insertData(epicLocalDtoList)

        //Then
        Truth.assertThat(
            epicLocalDataSource.observeEpic().first()
        ).isEqualTo(epicLocalDtoList)
    }

    @Test
    fun `get data from remote data source`() = runBlocking {
        //Given
        val remoteDtoList = EpicFactory.generateRemoteEpicList(4)

        //When
        stubServiceGetEpics(remoteDtoList)

        //Then
        Truth.assertThat(
            service.getEpics()
        ).isEqualTo(remoteDtoList)
    }

    private fun stubEpicLocalDataSourceObserveEpic(epicLocalList: Flow<List<EpicLocalDto>>) {
        Mockito.`when`(epicLocalDataSource.observeEpic())
            .thenReturn(epicLocalList)
    }

    private fun stubServiceGetEpics(epicRemoteList: List<EpicRemoteDto>) = runBlocking {
        Mockito.`when`(service.getEpics())
            .thenReturn(epicRemoteList)
    }

    private fun stubEpicLocalDataSourceInsertEpic(epicRemoteList: List<EpicLocalDto>, unit: Unit) =
        runBlocking {
            Mockito.`when`(epicLocalDataSource.insertData(epicRemoteList))
                .thenReturn(unit)
        }

}