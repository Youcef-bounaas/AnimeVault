package com.youcef_bounaas.animevault.domain.repository


import com.youcef_bounaas.animevault.domain.model.AnimeData


interface KitsuRepository {

    suspend fun getTrendingAnime(): List<AnimeData>

    suspend fun getAnime(id: Int): AnimeData?
}