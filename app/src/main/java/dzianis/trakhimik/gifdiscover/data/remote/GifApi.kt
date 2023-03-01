package dzianis.trakhimik.gifdiscover.data.remote

import dzianis.trakhimik.gifdiscover.data.remote.ApiConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("v1/gifs/trending?api_key=$API_KEY")
    suspend fun getGifs(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 50
    ): GifsDto
}