package com.example.youtube2.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtube2.core.base.BaseViewModel
import com.example.youtube2.data.model.PlayListModel
import com.example.youtube2.data.repository.Repository

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<com.example.youtube2.core.network.Resource<PlayListModel>> {
        return repository.getPlaylist()
    }
}