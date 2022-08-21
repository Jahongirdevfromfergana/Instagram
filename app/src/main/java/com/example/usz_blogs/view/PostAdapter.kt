package com.example.usz_blogs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usz_blogs.databinding.PostItemLayoutBinding
import com.example.usz_blogs.model.PostModel
import com.example.usz_blogs.model.UserModel
import java.text.SimpleDateFormat

interface PostItemAdapterListener{
    fun onClick(item: PostModel)
}

class PostAdapter(val items: List<PostModel>, val postItemAdapter: PostItemAdapterListener): RecyclerView.Adapter<PostAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: PostItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(PostItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            postItemAdapter.onClick(item)
        }
        holder.binding.postTitle.text = item.text
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val date = formatter.parse(item.publishDate)
        val outFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        holder.binding.postDate.text = outFormatter.format(date)
        holder.binding.like.text = item.likes.toString()
//        holder.binding.postDate.text = item.publishDate
        Glide.with(holder.itemView.context).load(item.image).into(holder.binding.postImage)
    }
    override fun getItemCount(): Int {
        return items.count()
    }
}