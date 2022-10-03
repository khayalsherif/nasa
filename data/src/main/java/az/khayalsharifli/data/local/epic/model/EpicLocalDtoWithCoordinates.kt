package az.khayalsharifli.data.local.epic.model

import androidx.room.Embedded
import androidx.room.Relation

data class EpicLocalDtoWithCoordinates(
    @Embedded val epicLocalDto: EpicLocalDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "child_id"
    )
    val centroid_coordinates: EpicLocalDto.CentroidCoordinates,
)