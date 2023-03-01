package dzianis.trakhimik.gifdiscover.presentation.ui.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun Gif(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(data = url).apply(
                block = {
                    size(Size.ORIGINAL)
                }
            ).build(),
            imageLoader = imageLoader
        ),
        contentDescription = contentDescription,
        modifier = modifier.height(200.dp).fillMaxWidth(),
    )
}