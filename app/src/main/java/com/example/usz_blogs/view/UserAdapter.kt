package com.example.usz_blogs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usz_blogs.databinding.UserItemLayoutBinding
import com.example.usz_blogs.model.UserModel


interface UserAdapterListener{
    fun onClick(item: UserModel)
}

class UserAdapter(val items: List<UserModel>, val adapterListener: UserAdapterListener): RecyclerView.Adapter<UserAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: UserItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            adapterListener.onClick(item)
        }
        holder.binding.userName.text = item.firstName
        Glide.with(holder.itemView.context).load(item.picture).into(holder.binding.userImg)
    }
    override fun getItemCount(): Int {
        return items.size
    }
}