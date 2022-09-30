package com.si.overlayapp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.si.overlayapp.databinding.ActivityVideoListBinding
import com.si.overlayapp.databinding.ItemRowBinding

class VideoListingActivity : BaseActivity<ActivityVideoListBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        //window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        val videoList = mutableListOf<Video>()

        videoList.add(Video("Hero ISL 2022-23 | The Highlanders always back their boys" ,"https://www.indiansuperleague.com/static-resources/waf-images/content/16/91/58/16-9/592-444/MFbP1rsF2A.jpg"))
        videoList.add(Video("Fans share their excitement for the Hero ISL 2022-23 season!" ,"https://www.indiansuperleague.com/static-resources/waf-images/content/c8/ec/1b/0/S8JeTv0Sm1.jpg"))
        videoList.add(Video("LFL Show - Masefield on East Bengal head coach Constantine's approach and philosophy" ,"https://www.indiansuperleague.com/static-resources/waf-images/content/db/05/cf/0/FBguhF2IAs.jpg"))
        videoList.add(Video("LFL Show - Shaiju Damodaran on NorthEast United FC's opening fixtures" ,"https://www.indiansuperleague.com/static-resources/waf-images/content/11/f1/99/0/fULEQsvkMJ.jpg"))

        val settingListAdapter = getListAdapter()
        binding.rvVideoList.apply {
            addItemDecoration(
                VerticalSpacingDecoration(
                    verticalOffset = 20
                )
            )
            adapter = settingListAdapter
        }
        settingListAdapter.submitList(videoList)
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }

    }


    override fun getLayoutId() = R.layout.activity_video_list

    private fun getListAdapter() = SimpleListAdapter(
        inflate = ItemRowBinding::inflate,
        onBind = { _: Int, itemRowMenuBinding: ItemRowBinding, data: Video ->
            itemRowMenuBinding.ivVideo.load(data.videoUrl)
            itemRowMenuBinding.tvTitle.text = data.videoTitle

            itemRowMenuBinding.root.setOnClickListener {
                startActivity(Intent(this@VideoListingActivity, MainActivity::class.java))
            }
        },
        itemComparator = object : DiffUtil.ItemCallback<Video>() {
            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Video,
                newItem: Video
            ): Boolean {
                return oldItem == newItem
            }

        }
    )

}