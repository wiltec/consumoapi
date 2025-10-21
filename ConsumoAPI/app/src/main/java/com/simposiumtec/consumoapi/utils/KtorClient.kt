package com.simposiumtec.consumoapi.utils

import com.simposiumtec.consumoapi.services.response.ClientResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

class KtorClient {
    private lateinit var client: HttpClient

    suspend fun post(url: String, requestJson: String): ClientResponse {
        client = HttpClient (CIO) {
            install(HttpTimeout){
                requestTimeoutMillis = Const.REQUEST_TIME_OUT.toLong()
            }
        }

        val baseResponse = ClientResponse()

        try {
            val httpResponse = client.post(url){
                setBody(TextContent(text = requestJson, contentType = ContentType.Application.Json))
            }

            baseResponse.status = httpResponse.status
            baseResponse.code  = httpResponse.status.value
            baseResponse.result = httpResponse.bodyAsText()
        }catch (ex: ClientRequestException){
            baseResponse.status = ex.response.status
            baseResponse.message = ex.message
            baseResponse.code = ex.response.status.value
            baseResponse.result = ex.response.bodyAsText()

        } finally {
            client.close()
        }

        return baseResponse
    }
}