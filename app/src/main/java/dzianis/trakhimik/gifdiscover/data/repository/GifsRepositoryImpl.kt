package dzianis.trakhimik.gifdiscover.data.repository

import dzianis.trakhimik.gifdiscover.data.remote.GifApi
import dzianis.trakhimik.gifdiscover.data.remote.toGifItem
import dzianis.trakhimik.gifdiscover.domain.models.GifItem
import dzianis.trakhimik.gifdiscover.domain.repository.GifsRepository
import dzianis.trakhimik.gifdiscover.domain.utils.Resource
import javax.inject.Inject

class GifsRepositoryImpl @Inject constructor(
    private val api: GifApi
) : GifsRepository {
    override suspend fun getGifs(offset: Int, limit: Int): Resource<List<GifItem>> {
        return try {
            Resource.Success(
                api.getGifs(offset, limit).data.map { x -> x.toGifItem() }
            )
        } catch (err: Exception) {
            Resource.Error(err.message ?: "Error of getting gifs, check internet connection")
        }
    }
}