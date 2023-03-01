package dzianis.trakhimik.gifdiscover.presentation.ui.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun GifCard(
    title: String,
    url: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            Text(
                text = title,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Gif(url = url, contentDescription = title)
        }
    }
}