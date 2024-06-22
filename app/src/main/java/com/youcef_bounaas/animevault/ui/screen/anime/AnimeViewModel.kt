package com.youcef_bounaas.animevault.ui.screen.anime


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youcef_bounaas.animevault.domain.model.AnimeData
import com.youcef_bounaas.animevault.domain.repository.KitsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val api: KitsuRepository
) : ViewModel() {

    private var _anime = MutableStateFlow<AnimeData?>(null)
    val anime = _anime.asStateFlow()

    fun fetchAnime(id: Int) {
        viewModelScope.launch {
            _anime.update { api.getAnime(id) }
        }
    }
}