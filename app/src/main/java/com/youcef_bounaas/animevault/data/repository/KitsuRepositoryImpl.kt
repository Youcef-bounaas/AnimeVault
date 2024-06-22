package com.youcef_bounaas.animevault.data.repository

import android.util.Log
import com.youcef_bounaas.animevault.data.network.KitsuApi
import com.youcef_bounaas.animevault.domain.model.AnimeData
import com.youcef_bounaas.animevault.domain.repository.KitsuRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import javax.inject.Inject

class KitsuRepositoryImpl @Inject constructor(
    private val api: KitsuApi
): KitsuRepository {

    override suspend fun getTrendingAnime(): List<AnimeData> {
        var animeData: List<AnimeData> = emptyList()

        api.getTrendingAnime()
            .onSuccess {
                Log.d("getTrendingAnime", "success")
                animeData = data.toModel()
            }
            .onError {
                Log.d("getTrendingAnime", "error - ${this.message()}")
            }
            .onException {
                Log.d("getTrendingAnime", "exception - ${this.message}")
            }

        return animeData
    }

    override suspend fun getAnime(id: Int): AnimeData? {
        var anime: AnimeData? = null
        api.getAnime(id)
            .onSuccess {
                Log.d("getAnime", "success")

                anime = data?.toModel()
            }
            .onError {
                Log.d("getAnime", "error - ${this.message()}")
            }
            .onException {
                Log.d("getAnime", "exception - ${this.message}")
            }

        return anime
    }
}