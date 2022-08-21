package com.example.usz_blogs.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.usz_blogs.api.ApiService
import com.example.usz_blogs.api.BaseResponse
import com.example.usz_blogs.databinding.ActivityMainBinding
import com.example.usz_blogs.model.PostModel
import com.example.usz_blogs.model.UserModel
import com.example.usz_blogs.screen.post.DetailActivity
import com.example.usz_blogs.screen.post.PostDetailActivity
import com.example.usz_blogs.view.PostAdapter
import com.example.usz_blogs.view.PostItemAdapterListener
import com.example.usz_blogs.view.UserAdapter
import com.example.usz_blogs.view.UserAdapterListener
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipe.setOnRefreshListener(this)
        binding.swipe.isRefreshing = true
        loadPosts()
        loadUsers()
    }
    fun loadUsers() {
        ApiService.apiClient().getUsers()
            .enqueue(object : retrofit2.Callback<BaseResponse<List<UserModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<UserModel>>>,
                    response: Response<BaseResponse<List<UserModel>>>
                ) {
                    binding.swipe.isRefreshing = false
                    binding.userRe.adapter = UserAdapter(response.body()?.data ?: emptyList(), object :
                        UserAdapterListener {
                        override fun onClick(item: UserModel) {
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra("extra_user", item)
                            startActivity(intent)
                        }
                    })
                    binding.userRe.layoutManager =
                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                }

                override fun onFailure(call: Call<BaseResponse<List<UserModel>>>, t: Throwable) {
                    binding.swipe.isRefreshing = false
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }
    fun loadPosts() {
        ApiService.apiClient().getPost()
            .enqueue(object : retrofit2.Callback<BaseResponse<List<PostModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<PostModel>>>,
                    response: Response<BaseResponse<List<PostModel>>>
                ) {

                    binding.postRe.adapter = PostAdapter(response.body()?.data ?: emptyList()
                        , object : PostItemAdapterListener{
                        override fun onClick(item: PostModel) {
                            val intent = Intent(this@MainActivity, PostDetailActivity::class.java)
                            intent.putExtra("extra_post", item)
                            startActivity(intent)
                        }
                    })
                    binding.postRe.layoutManager =
                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                }

                override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }
    override fun onRefresh() {
        loadPosts()
        loadUsers()
    }
}