package az.khayalsharifli.data.local.epic

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface EpicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpic(list: List<EpicLocalDto>)

    @Query("SELECT * FROM epic")
    fun observeEpic(): Flow<List<EpicLocalDto>>

    @Query("DELETE FROM epic")
    fun clearEpic()

}