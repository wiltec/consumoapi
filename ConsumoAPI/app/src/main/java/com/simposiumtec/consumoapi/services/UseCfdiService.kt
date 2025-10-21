package com.simposiumtec.consumoapi.services

import com.google.gson.Gson
import com.simposiumtec.consumoapi.services.request.FindUseCfdiRequest
import com.simposiumtec.consumoapi.services.response.ClientResponse
import com.simposiumtec.consumoapi.utils.Const
import com.simposiumtec.consumoapi.utils.KtorClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UseCfdiService {
    private var ktorClient: KtorClient = KtorClient()

    fun find(request: FindUseCfdiRequest): ClientResponse  {
        val url = Const.BASE_URL_API+"/sat/usecfdi/find"
        val request = Gson().toJson(request)
        var result = ClientResponse()

        runBlocking {
            val response = withContext(Dispatchers.IO) {
                ktorClient.post(url, request)
            }

            result = response
        }

        return result
    }
}