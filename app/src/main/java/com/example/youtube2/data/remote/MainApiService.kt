package com.example.youtube2.data.remote

import com.example.youtube2.data.model.PlayListItemModel
import com.example.youtube2.data.model.PlayListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {
    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlayListModel>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int
    ): Response<PlayListItemModel>
}