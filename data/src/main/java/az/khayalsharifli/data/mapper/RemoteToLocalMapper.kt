package az.khayalsharifli.data.mapper

import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.data.remote.model.EpicRemoteDto


fun EpicRemoteDto.CentroidCoordinates.toLocal(): EpicLocalDto.CentroidCoordinates =
    EpicLocalDto.CentroidCoordinates(lat = lat, lon = lon)

fun EpicRemoteDto.toLocal(): EpicLocalDto =
    EpicLocalDto(
        caption = caption,
        centroid_coordinates = centroid_coordinates.toLocal(),
        date = date,
        identifier = identifier,
        image = image,
        version = version
    )