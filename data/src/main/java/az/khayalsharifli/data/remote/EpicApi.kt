package az.khayalsharifli.data.remote

import az.khayalsharifli.data.remote.model.EpicRemoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpicApi {
    @GET("/EPIC/api/natural/images")
    suspend fun getEpics(
        @Query("api_key") key: String = "wtDl5n1USYfB06iMdKnbcJWTn93dOL129LkFaWiS"
    ): List<EpicRemoteDto>
}