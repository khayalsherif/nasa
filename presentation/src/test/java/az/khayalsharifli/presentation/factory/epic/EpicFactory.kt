package az.khayalsharifli.presentation.factory.epic

import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.presentation.factory.DataFactory

object EpicFactory {

    fun generateDomainEpicList(size: Int): List<Epic> {
        val arrayList = ArrayList<Epic>()
        repeat(size) {
            val coordinates = Epic.CentroidCoordinates(
                lat = DataFactory.getRandomDouble(),
                lon = DataFactory.getRandomDouble()
            )
            val epic = Epic(
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

    fun generateDomainEpic(): Epic {
        val coordinates = Epic.CentroidCoordinates(
            lat = DataFactory.getRandomDouble(),
            lon = DataFactory.getRandomDouble()
        )
        return Epic(
            caption = DataFactory.getRandomString(),
            centroid_coordinates = coordinates,
            date = DataFactory.getRandomString(),
            identifier = DataFactory.getRandomString(),
            image = DataFactory.getRandomString(),
            version = DataFactory.getRandomString()
        )
    }

}