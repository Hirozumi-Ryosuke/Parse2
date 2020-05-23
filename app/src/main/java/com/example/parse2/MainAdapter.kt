package com.example.parse2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitle = listOf("First title", "Second", "3rd")

    // number of items
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //val videoTitle = videoTitle[position]
        val video = homeFeed.videos[position]
        holder.view.textView_video_title.text = video.name

        holder.view.textView_channel_name.text = video.channel.name + " * " + "20K Views\n4 days ago "

        val thumbnailImageView = holder.view.imageView_video_thumbnail
        thumbnailImageView.load(video.imageUrl)

        val channelProfileImageView = holder.view.imageView_channel_profile
        channelProfileImageView.load(video.channel.profileImageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        holder.video = video
    }
}

class CustomViewHolder(val view: View, var video: Video? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_ID_KEY = "VIDEO_ID"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CourseDetailActivity::class.java)

            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)

            view.context.startActivity(intent)
        }
    }
}