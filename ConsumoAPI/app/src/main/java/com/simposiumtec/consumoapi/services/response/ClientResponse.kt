package com.simposiumtec.consumoapi.services.response

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
class ClientResponse {
    var status: HttpStatusCode? = null
    var code: Int? = null
    var message: String = ""
    var error: String = ""
    var result: String = ""
    var resultByte: ByteArray = byteArrayOf()
}