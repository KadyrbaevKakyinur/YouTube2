package com.example.youtube2.core.network

import com.example.youtube2.core.base.BaseDataSource
import com.example.youtube2.BuildConfig
import com.example.youtube2.data.remote.MainApiService
import com.example.youtube2.utils.Constants

class RemoteDataSource(private val apiService: MainApiService) : BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(
            key = BuildConfig.API_KEY,
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            maxResults = 10
        )
    }

    suspend fun getDetail(playlistId: String) = getResult {
        apiService.getPlaylistItems(
            key = BuildConfig.API_KEY,
            part = Constants.PART,
            playlistId = playlistId,
            maxResults = 20
        )
    }
}