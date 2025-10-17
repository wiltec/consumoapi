package com.simposiumtec.consumoapi.services.request

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val title: String,
    val body: String,
    val userId: Int
)
