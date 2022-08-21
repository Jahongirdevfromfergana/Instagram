package com.example.usz_blogs.model

import java.io.Serializable
import java.security.acl.Owner

data class PostModel(
    val id: String,
    val image: String,
    val text: String,
    val publishDate: String,
    val likes: Int,
    val owner: Owners
) : Serializable

data class Owners(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String
) : Serializable