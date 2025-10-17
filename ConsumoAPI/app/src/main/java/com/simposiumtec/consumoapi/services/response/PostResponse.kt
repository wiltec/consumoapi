package com.simposiumtec.consumoapi.services.response

import kotlinx.serialization.Serializable


@Serializable
data class PostResponse(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)