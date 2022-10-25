package az.khayalsharifli.data.repository

import az.khayalsharifli.data.local.epic.EpicLocalDataSource
import az.khayalsharifli.data.mapper.epic.EpicMapper
import az.khayalsharifli.data.remote.EpicService
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class EpicRepositoryTest {

    @Mock
    lateinit var service: EpicService

    @Mock
    lateinit var epicLocalDataSource: EpicLocalDataSource

    private lateinit var epicMapper: EpicMapper

    private lateinit var epicRepositoryImpl: EpicRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        epicMapper = EpicMapper()
        epicRepositoryImpl = EpicRepositoryImpl(
            mapper = epicMapper,
            epicService = service,
            epicLocalDataSource = epicLocalDataSource
        )
    }

}