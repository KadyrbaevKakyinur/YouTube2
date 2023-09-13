package com.example.youtube2.ui.detail

import androidx.lifecycle.LiveData
import com.example.youtube2.core.base.BaseViewModel
import com.example.youtube2.data.model.PlayListItemModel
import com.example.youtube2.data.repository.Repository

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<com.example.youtube2.core.network.Resource<PlayListItemModel>> {
        return repository.getPlaylistItems(playlistId)
    }
}