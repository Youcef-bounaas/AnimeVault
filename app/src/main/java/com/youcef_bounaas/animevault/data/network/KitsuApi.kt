package com.youcef_bounaas.animevault.data.network

import com.skydoves.sandwich.ApiResponse
import com.youcef_bounaas.animevault.data.network.dto.AnimeResponseDto
import com.youcef_bounaas.animevault.data.network.dto.TrendingAnimeListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface KitsuApi {

    @GET("trending/anime")
    suspend fun getTrendingAnime(): ApiResponse<TrendingAnimeListDto>

    @GET("anime/{id}")
    suspend fun getAnime(
        @Path("id") id: Int
    ): ApiResponse<AnimeResponseDto?>

    companion object {
        const val baseUrl = "https://kitsu.io/api/edge/"
    }
}