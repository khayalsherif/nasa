package az.khayalsharifli.data.factory.epic

import az.khayalsharifli.data.factory.DataFactory
import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.data.remote.model.EpicRemoteDto
import az.khayalsharifli.domain.model.Epic

object EpicFactory {

    fun generateLocalEpicList(size: Int): List<EpicLocalDto> {
        val arrayList = ArrayList<EpicLocalDto>()
        repeat(size) {
            val coordinates = EpicLocalDto.CentroidCoordinates(
                lat = DataFactory.getRandomDouble(),
                lon = DataFactory.getRandomDouble()
            )
            val epic = EpicLocalDto(
                caption = DataFactory.getRandomString(),
                centroid_coordinates = coordinates,
                date = DataFactory.getRandomString(),
                identifier = DataFactory.getRandomString(),
                image = DataFactory.getRandomString(),
                version = DataFactory.getRandomString()
            )
            arrayList.add(epic)
        }

        return arrayList
    }


    fun generateRemoteEpicList(size: Int): List<EpicRemoteDto> {
        val arrayList = ArrayList<EpicRemoteDto>()
        repeat(size) {
            val coordinates = EpicRemoteDto.CentroidCoordinates(
                lat = DataFactory.getRandomDouble(),
                lon = DataFactory.getRandomDouble()
            )
            val epic = EpicRemoteDto(
                caption = DataFactory.getRandomString(),
                centroid_coordinates = coordinates,
                date = DataFactory.getRandomString(),
                identifier = DataFactory.getRandomString(),
                image = DataFactory.getRandomString(),
                version = DataFactory.getRandomString()
            )
            arrayList.add(epic)
        }

        return arrayList
    }

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