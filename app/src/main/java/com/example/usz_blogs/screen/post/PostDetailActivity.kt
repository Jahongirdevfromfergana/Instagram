package com.example.usz_blogs.screen.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.usz_blogs.databinding.ActivityPostDetailBinding
import com.example.usz_blogs.model.PostModel
import com.example.usz_blogs.model.UserModel
import java.text.SimpleDateFormat

class PostDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostDetailBinding

    lateinit var post: PostModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        post = intent.getSerializableExtra("extra_post") as PostModel

        Glide.with(this).load(post.owner.picture).into(binding.userImg)
        binding.userName.text = "${post.owner.firstName} ${post.owner.lastName}"


        Glide.with(this).load(post.image).into(binding.postImage)
        binding.postTitle.text = post.text
        binding.like.text = post.likes.toString()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val date = formatter.parse(post.publishDate)
        val outFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        binding.postDate.text = outFormatter.format(date)
        binding.like.text = post.likes.toString()

        binding.back.setOnClickListener {
            finish()
        }












    }
}