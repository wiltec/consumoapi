package com.simposiumtec.consumoapi.services.response

import kotlinx.serialization.Serializable

@Serializable
class BaseResponse: OpenBaseResponse() {
    lateinit var  Records: MutableList<*>
}