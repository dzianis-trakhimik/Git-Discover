package dzianis.trakhimik.gifdiscover.presentation

import dzianis.trakhimik.gifdiscover.domain.models.GifItem

data class GifsState(
    val currentOffset: Int = 0,
    val gifs: List<GifItem> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)