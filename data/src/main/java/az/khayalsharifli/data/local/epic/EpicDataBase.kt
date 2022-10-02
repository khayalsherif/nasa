package az.khayalsharifli.data.local.epic

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import az.khayalsharifli.data.local.epic.model.EpicLocalDto

@Database(
    entities = [EpicLocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class EpicDataBase : RoomDatabase() {
    abstract fun epicDao(): EpicDao
}