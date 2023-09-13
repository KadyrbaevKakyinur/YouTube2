package com.example.youtube2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube2.core.network.RemoteDataSource
import com.example.youtube2.core.network.Resource
import com.example.youtube2.data.model.PlayListItemModel
import com.example.youtube2.data.model.PlayListModel
import kotlinx.coroutines.Dispatchers


class Repository(private val remoteDataSource: RemoteDataSource) {

    fun getPlaylist(): LiveData<Resource<PlayListModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getPlaylist())
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlayListItemModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getDetail(playlistId))
        }
    }
}