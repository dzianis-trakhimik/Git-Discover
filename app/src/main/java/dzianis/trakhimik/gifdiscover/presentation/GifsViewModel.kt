package dzianis.trakhimik.gifdiscover.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dzianis.trakhimik.gifdiscover.domain.repository.GifsRepository
import dzianis.trakhimik.gifdiscover.domain.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    private val repository: GifsRepository
) : ViewModel() {
    var state by mutableStateOf(GifsState())
    private set

    fun loadGifs(isReload: Boolean = false) {
        if (state.isLoading) return
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null,
                currentOffset = if (isReload) 0 else state.currentOffset
            )
            repository.getGifs(offset = state.currentOffset).let { result ->
                when(result) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            gifs = state.gifs + result.data!!,
                            currentOffset = state.currentOffset + result.data.size
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}