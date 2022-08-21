package com.example.usz_blogs.screen.post

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.usz_blogs.api.ApiService
import com.example.usz_blogs.api.BaseResponse
import com.example.usz_blogs.databinding.ActivityDetailBinding
import com.example.usz_blogs.model.PostModel
import com.example.usz_blogs.model.UserModel
import com.example.usz_blogs.view.PostAdapter
import com.example.usz_blogs.view.PostItemAdapterListener
import com.example.usz_blogs.view.UserAdapterListener
import retrofit2.Call
import retrofit2.Response


class DetailActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var user: UserModel
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("extra_user") as UserModel

        binding.textName.text = user.firstName+" "+user.lastName

        binding.swipe.setOnRefreshListener(this)
        binding.back.setOnClickListener {
            finish()
        }
        loadPost()
    }

    override fun onRefresh() {
        loadPost()
    }
    fun loadPost(){
        binding.swipe.isRefreshing = true
        ApiService.apiClient().getPostByUser(user.id)
            .enqueue(object : retrofit2.Callback<BaseResponse<List<PostModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<PostModel>>>,
                    response: Response<BaseResponse<List<PostModel>>>
                ) {
                    binding.swipe.isRefreshing = false
                    binding.postItemRec.adapter = PostAdapter(response.body()?.data ?: emptyList(), object : PostItemAdapterListener{
                        override fun onClick(item: PostModel) {

                        }
                    })
                    binding.postItemRec.layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.VERTICAL, false)
                }
                override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                    binding.swipe.isRefreshing = false
                    Toast.makeText(this@DetailActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }
}