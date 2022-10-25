package az.khayalsharifli.data.factory.epic

import az.khayalsharifli.data.factory.DataFactory
import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.data.remote.model.EpicRemoteDto

object EpicFactory {

    fun generateLocalEpic(): EpicLocalDto {
        val coordinates = EpicLocalDto.CentroidCoordinates(
            lat = DataFactory.getRandomDouble(),
            lon = DataFactory.getRandomDouble()
        )
        return EpicLocalDto(
            caption = DataFactory.getRandomString(),
            centroid_coordinates = coordinates,
            date = DataFactory.getRandomString(),
            identifier = DataFactory.getRandomString(),
            image = DataFactory.getRandomString(),
            version = DataFactory.getRandomString()
        )
    }


    fun generateRemoteEpic(): EpicRemoteDto {
        val coordinates = EpicRemoteDto.CentroidCoordinates(
            lat = DataFactory.getRandomDouble(),
            lon = DataFactory.getRandomDouble()
        )
        return EpicRemoteDto(
            caption = DataFactory.getRandomString(),
            centroid_coordinates = coordinates,
            date = DataFactory.getRandomString(),
            identifier = DataFactory.getRandomString(),
            image = DataFactory.getRandomString(),
            version = DataFactory.getRandomString()
        )
    }

}