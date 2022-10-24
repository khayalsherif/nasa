package az.khayalsharifli.data.repository

import az.khayalsharifli.data.local.epic.EpicLocalDataSource
import az.khayalsharifli.data.mapper.epic.EpicMapper
import az.khayalsharifli.data.remote.EpicService
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.repository.EpicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpicRepositoryImpl(
    private val mapper: EpicMapper,
    private val epicService: EpicService,
    private val epicLocalDataSource: EpicLocalDataSource
) : EpicRepository {

    override fun observeEpics(): Flow<List<Epic>> {
        return epicLocalDataSource.observeEpic()
            .map { list -> list.map { mapper.localToDomain(it) } }
    }

    override suspend fun sync() {
        val remoteEpics = epicService.getEpics()
        val localEpics = remoteEpics.map { mapper.remoteToLocal(it) }
        epicLocalDataSource.insertData(localEpics)
    }

}