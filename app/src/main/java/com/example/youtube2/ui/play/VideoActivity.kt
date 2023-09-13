package com.example.youtube2.ui.play

import android.annotation.SuppressLint
import android.view.View
import com.example.youtube2.core.base.BaseActivity
import com.example.youtube2.databinding.ActivityVideoBinding
import com.example.youtube2.utils.ConnectionLiveData
import com.example.youtube2.utils.Constants.VIDEO_DESCRIPTION
import com.example.youtube2.utils.Constants.VIDEO_ITEM_ID
import com.example.youtube2.utils.Constants.VIDEO_TITLE
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity() : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

    override fun inflateViewBinding(): ActivityVideoBinding =
        ActivityVideoBinding.inflate(layoutInflater)

    override val viewModel: VideoViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun initLiveData() {
        super.initLiveData()
        val getIntentItemId = intent.getStringExtra(VIDEO_ITEM_ID)
        val getIntentVideoId = intent.getStringExtra(VIDEO_ITEM_ID)
        val getIntentDesc = intent.getStringExtra(VIDEO_DESCRIPTION)
        val getIntentTitle = intent.getStringExtra(VIDEO_TITLE)
    }

    override fun initView() {
        super.initView()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        with(binding) {
            tvTitle.text
        }
    }

    override fun initListener() {
        super.initListener()
        binding.llBack.setOnClickListener {
            finish()
        }
    }

    override fun checkInternetConnection() {
        super.checkInternetConnection()
        ConnectionLiveData(application).observe(this) { isConnection ->
            if (!isConnection) {
                binding.mainContainer.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
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
}