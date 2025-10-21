package com.simposiumtec.consumoapi.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.simposiumtec.consumoapi.databinding.UsecfdiLayoutBinding
import com.simposiumtec.consumoapi.models.UseCfdiAdapterModel
import com.simposiumtec.consumoapi.recycleradapter.UseCfdiAdapter
import com.simposiumtec.consumoapi.services.UseCfdiService
import com.simposiumtec.consumoapi.services.request.FindUseCfdiRequest
import com.simposiumtec.consumoapi.services.request.PaginationRequest
import com.simposiumtec.consumoapi.services.response.BaseResponse
import com.simposiumtec.consumoapi.services.response.UseCfdiResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCfdiActivity : AppCompatActivity() {

    private lateinit var binding: UsecfdiLayoutBinding
    private lateinit var adapter: UseCfdiAdapter
    private var listAdapterModel = mutableListOf<UseCfdiAdapterModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsecfdiLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }
        refreshData()
    }

    private fun loadAdapter(){
        adapter = UseCfdiAdapter(listAdapterModel)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    private fun refreshData(){
        val request = FindUseCfdiRequest(
            Pagination = PaginationRequest("1","100")
        )
        findUseCfdi(request)
    }

    private fun findUseCfdi(request: FindUseCfdiRequest) {
        listAdapterModel.clear()
        binding.rvList.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    UseCfdiService().find(request)
                }

                if (response.status == HttpStatusCode.OK) {
                    val data = Gson().fromJson(response.result, BaseResponse::class.java)

                    if (data.Code == "999") {
                        var recordJson = Gson().toJson(data.Records)
                        var result = Gson().fromJson(recordJson, Array<UseCfdiResponse>::class.java)

                        for (item in result) {
                            listAdapterModel.add(
                                UseCfdiAdapterModel(
                                    IdUserCfdi = item.IdUseCfdi,
                                    Description = item.Description,
                                    CodeUseCfdi = item.CodeUseCfdi,
                                    AppliesPhysicalPerson = item.AppliesPhysicalPerson,
                                    AppliesMoralPerson = item.AppliesMoralPerson
                                )
                            )
                        }

                        loadAdapter()

                    }
                }

            }catch (ex: Exception) {

            }finally {
                binding.rvList.visibility = View.VISIBLE
                binding.swipeRefresh.isRefreshing = false
            }
        }

    }
}