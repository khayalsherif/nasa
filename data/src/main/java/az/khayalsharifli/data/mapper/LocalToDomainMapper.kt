package az.khayalsharifli.data.mapper

import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.domain.model.Epic


/*fun EpicLocalDto.CentroidCoordinates.toDomain(): Epic.CentroidCoordinates =
    Epic.CentroidCoordinates(lat = lat, lon = lon)*/

fun EpicLocalDto.toDomain(): Epic =
    Epic(
        caption = caption,
        //centroid_coordinates = centroid_coordinates.toDomain(),
        date = date,
        identifier = identifier,
        image = image,
        version = version
    )


