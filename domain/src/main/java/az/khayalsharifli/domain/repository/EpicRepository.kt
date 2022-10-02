package az.khayalsharifli.domain.repository

import az.khayalsharifli.domain.model.Epic
import kotlinx.coroutines.flow.Flow

interface EpicRepository {
    fun observeEpics(): Flow<List<Epic>>
    suspend fun sync()
}