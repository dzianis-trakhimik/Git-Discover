package dzianis.trakhimik.gifdiscover.data.remote

import com.squareup.moshi.Json
import dzianis.trakhimik.gifdiscover.domain.models.GifItem

data class GifItemDto(
    val title: String,
    val type: String,
    val images: GifImagesDto
)

data class GifImagesDto(
    @field:Json(name = "fixed_height")
    val fixedHeight: GifImageDto,
    val original: GifImageDto
)

data class GifImageDto(
    val url: String
)

fun GifItemDto.toGifItem(): GifItem {
    return GifItem(title = this.title, url = this.images.fixedHeight.url)
}
