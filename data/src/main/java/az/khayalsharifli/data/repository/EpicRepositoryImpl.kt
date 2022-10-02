package az.khayalsharifli.data.repository

import az.khayalsharifli.data.local.epic.EpicLocalDataSource
import az.khayalsharifli.data.mapper.toDomain
import az.khayalsharifli.data.mapper.toLocal
import az.khayalsharifli.data.remote.EpicApi
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.repository.EpicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpicRepositoryImpl(
    private val epicApi: EpicApi,
    private val epicLocalDataSource: EpicLocalDataSource
) : EpicRepository {

    override fun observeEpics(): Flow<List<Epic>> {
        return epicLocalDataSource.observeEpic().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun sync() {
        val remoteEpics = epicApi.getEpics()
        val localEpics = remoteEpics.map { it.toLocal() }
        epicLocalDataSource.insertData(localEpics)
    }

}