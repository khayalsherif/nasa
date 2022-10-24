package az.khayalsharifli.domain.factory.epic

import az.khayalsharifli.domain.factory.DataFactory
import az.khayalsharifli.domain.model.Epic

object EpicFactory {

    fun generateDummyEpicList(size: Int): List<Epic> {
        val movieList = mutableListOf<Epic>()
        repeat(size) {
            movieList.add(generateEpic())
        }
        return movieList
    }


    private fun generateEpicCoordinate() =
        Epic.CentroidCoordinates(
            DataFactory.getRandomDouble(),
            DataFactory.getRandomDouble()
        )

    private fun generateEpic() = Epic(
        DataFactory.getRandomString(),
        generateEpicCoordinate(),
        DataFactory.getRandomString(),
        DataFactory.getRandomString(),
        DataFactory.getRandomString(),
        DataFactory.getRandomString()
    )
}