package az.khayalsharifli.domain.model

//Epic is a Earth Polychromatic Imaging Camera

data class Epic(
    val caption: String,
    //val centroid_coordinates: CentroidCoordinates,
    val date: String,
    val identifier: String,
    val image: String,
    val version: String
) {
    data class CentroidCoordinates(
        val lat: Double,
        val lon: Double
    )

}

