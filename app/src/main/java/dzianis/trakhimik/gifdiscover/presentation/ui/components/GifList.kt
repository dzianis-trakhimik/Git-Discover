package dzianis.trakhimik.gifdiscover.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dzianis.trakhimik.gifdiscover.domain.models.GifItem

@Composable
fun GifList(
    items: List<GifItem>,
    onLoadNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var lazyColumnState = rememberLazyListState()
    LaunchedEffect(key1 = (lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) > items.size - 3) {
        onLoadNext()
    }
    LazyColumn(
        state = lazyColumnState,
        modifier = modifier
    ) {
        items(items) { item ->
            GifCard(
                title = item.title,
                url = item.url,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}