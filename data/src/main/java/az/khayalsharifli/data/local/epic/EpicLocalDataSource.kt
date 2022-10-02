package az.khayalsharifli.data.local.epic

import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import kotlinx.coroutines.flow.Flow

interface EpicLocalDataSource {
    fun observeEpic(): Flow<List<EpicLocalDto>>
    suspend fun insertData(epics: List<EpicLocalDto>)
}