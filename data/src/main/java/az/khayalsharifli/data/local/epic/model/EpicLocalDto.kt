package az.khayalsharifli.data.local.epic.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Epic is a Earth Polychromatic Imaging Camera

@Entity(tableName = "epic")
data class EpicLocalDto(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "caption") val caption: String,
    //@ColumnInfo(name = "centroid_coordinates") val centroid_coordinates: CentroidCoordinates,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "identifier") val identifier: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "version") val version: String
) {
    /*@Entity(tableName = "coordinates")
    data class CentroidCoordinates(
        @ColumnInfo(name = "lat") val lat: Double,
        @ColumnInfo(name = "lon") val lon: Double
    )*/
}


