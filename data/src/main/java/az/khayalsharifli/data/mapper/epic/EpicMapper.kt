package az.khayalsharifli.data.mapper.epic

import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.data.mapper.Mapper
import az.khayalsharifli.data.remote.model.EpicRemoteDto
import az.khayalsharifli.domain.model.Epic

typealias LocalCoordinates = EpicLocalDto.CentroidCoordinates
typealias DomainCoordinates = Epic.CentroidCoordinates

class EpicMapper : Mapper<EpicRemoteDto, EpicLocalDto, Epic> {
    override fun remoteToLocal(type: EpicRemoteDto): EpicLocalDto {
        val centroidCoordinates = LocalCoordinates(
            lat = type.centroid_coordinates.lat,
            lon = type.centroid_coordinates.lon
        )
        return EpicLocalDto(
            caption = type.caption,
            centroid_coordinates = centroidCoordinates,
            date = type.date,
            identifier = type.identifier,
            image = type.image,
            version = type.version
        )
    }

    override fun localToDomain(type: EpicLocalDto): Epic {
        val centroidCoordinates = DomainCoordinates(
            lat = type.centroid_coordinates.lat,
            lon = type.centroid_coordinates.lon
        )
        return Epic(
            caption = type.caption,
            centroid_coordinates = centroidCoordinates,
            date = type.date,
            identifier = type.identifier,
            image = type.image,
            version = type.version
        )
    }

}


