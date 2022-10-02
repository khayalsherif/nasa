package az.khayalsharifli.data.local.epic

import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class EpicLocalDataSourceImpl(
    private val epicDao: EpicDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : EpicLocalDataSource {
    override fun observeEpic(): Flow<List<EpicLocalDto>> {
        return epicDao.observeEpic()
    }

    override suspend fun insertData(epics: List<EpicLocalDto>) {
        withContext(dispatcher) {
            epicDao.clearEpic()
            //epicDao.clearCoordinates()
            //epicDao.insertEpicCoordinates(epics.map { it.centroid_coordinates })
            epicDao.insertEpic(epics)
        }
    }

}