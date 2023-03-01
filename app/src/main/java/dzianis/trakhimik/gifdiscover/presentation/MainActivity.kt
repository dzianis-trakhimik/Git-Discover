package dzianis.trakhimik.gifdiscover.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import dzianis.trakhimik.gifdiscover.domain.models.GifItem
import dzianis.trakhimik.gifdiscover.presentation.ui.components.GifList
import dzianis.trakhimik.gifdiscover.presentation.ui.theme.GifDiscoverTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GifsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadGifs()
        setContent {
            GifDiscoverTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        state = viewModel.state,
                        onRefresh = { viewModel.loadGifs(true) },
                        onLoadNext = { viewModel.loadGifs() }
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    state: GifsState,
    onRefresh: () -> Unit,
    onLoadNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize()
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
        ) {
            GifsAndNetworkState(
                gifList = state.gifs,
                isLoading = state.isLoading,
                error = state.error,
                onLoadNext = onLoadNext
            )
        }
    }
}

@Composable
fun GifsAndNetworkState(
    gifList: List<GifItem>,
    isLoading: Boolean = false,
    error: String? = null,
    onLoadNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (isLoading) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .wrapContentWidth(CenterHorizontally),
                text = "Loading...",
            )
        }
        if (!error.isNullOrBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
                    .background(MaterialTheme.colors.error),
                text = error,
                color = MaterialTheme.colors.onError
            )
        }
        GifList(
            items = gifList,
            onLoadNext = onLoadNext,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        )
    }
}

