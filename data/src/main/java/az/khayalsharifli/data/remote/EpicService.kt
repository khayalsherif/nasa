package az.khayalsharifli.data.remote

import az.khayalsharifli.data.BuildConfig
import az.khayalsharifli.data.remote.model.EpicRemoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpicService {
    @GET("/EPIC/api/natural/images")
    suspend fun getEpics(
        @Query("api_key") key: String = BuildConfig.NASA_API_KEY
    ): List<EpicRemoteDto>
}