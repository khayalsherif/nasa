package az.khayalsharifli.domain.usecase

import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.domain.repository.EpicRepository
import kotlinx.coroutines.flow.Flow

class EpicUseCase(private val epicRepository: EpicRepository) {

    fun getData(): Flow<List<Epic>> {
        return epicRepository.observeEpics()
    }

    suspend fun executeBackground() {
        epicRepository.sync()
    }
}