package dzianis.trakhimik.gifdiscover.domain.repository

import dzianis.trakhimik.gifdiscover.domain.models.GifItem
import dzianis.trakhimik.gifdiscover.domain.utils.Resource

interface GifsRepository {
    suspend fun getGifs(offset: Int, limit: Int = 50): Resource<List<GifItem>>
}