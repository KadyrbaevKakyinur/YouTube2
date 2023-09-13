package com.example.youtube2.ui.playlist


import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.youtube2.core.base.BaseActivity
import com.example.youtube2.core.network.Resource
import com.example.youtube2.data.model.PlayListModel
import com.example.youtube2.databinding.ActivityMainBinding
import com.example.youtube2.ui.detail.DetailActivity
import com.example.youtube2.utils.ConnectionLiveData
import com.example.youtube2.utils.Constants.PLAYLIST_DESCRIPTION
import com.example.youtube2.utils.Constants.PLAYLIST_ID
import com.example.youtube2.utils.Constants.PLAYLIST_TITLE
import com.example.youtube2.utils.Constants.VIDEO_ITEM_COUNT
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override val viewModel: MainViewModel by viewModel()

    private val adapter = MainAdapter(this::onClick)

    override fun initLiveData() {
        super.initLiveData()
        viewModel.getPlayList().observe(this@MainActivity) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(this, "success status", Toast.LENGTH_SHORT).show()
                    adapter.setList(response.data?.items)
                    viewModel.loading.value = false
                }

                Resource.Status.ERROR -> {
                    Toast.makeText(this, "error status", Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = false

                }

                Resource.Status.LOADING -> {
                    Toast.makeText(this, "loading status", Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = true
                }
            }
        }
        viewModel.loading.observe(this) { status ->
            if (status) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }
    }

    override fun checkInternetConnection() {
        super.checkInternetConnection()
        ConnectionLiveData(application).observe(this) { isConnection ->
            if (!isConnection) {
                binding.noConnection.visibility = View.VISIBLE
                binding.mainContainer.visibility = View.GONE
            }
            binding.noInternetConnectionInclude.btnTryAgain.setOnClickListener {
                if (!isConnection) {
                    binding.mainContainer.visibility = View.GONE
                    binding.noConnection.visibility = View.VISIBLE
                } else {
                    binding.mainContainer.visibility = View.VISIBLE
                    binding.noConnection.visibility = View.GONE
                }
            }
        }
    }

    private fun onClick(item: PlayListModel.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PLAYLIST_ID, item.id)
        intent.putExtra(PLAYLIST_TITLE, item.snippet.title)
        intent.putExtra(PLAYLIST_DESCRIPTION, item.snippet.description)
        intent.putExtra(VIDEO_ITEM_COUNT, item.contentDetails.itemCount.toString())
        startActivity(intent)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
    }
}