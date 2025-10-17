package com.simposiumtec.consumoapi.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.simposiumtec.consumoapi.utils.KtorClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCfdiActivity : AppCompatActivity(), UseCfdiAdapter.OnItemClickListener {

    private lateinit var binding: UsecfdiLayoutBinding
    private lateinit var adapter: UseCfdiAdapter
    private var listAdapterModel = mutableListOf<UseCfdiAdapterModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsecfdiLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        refreshData()
        binding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        val request = FindUseCfdiRequest(
            Pagination = PaginationRequest("1", PerPager = "100")
        )
        findUseCfdi(request)
    }

    private fun findUseCfdi(request: FindUseCfdiRequest){
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
                                    AplliesMoralPerson = item.AppliesMoralPerson,
                                    AppliesPhysicalPerson = item.AppliesPhysicalPerson
                                )
                            )
                        }

                        loadAdapter()
                    }

                }
            }catch (ex: Exception) {
                Toast.makeText(binding.root.context, ex.message.toString(), Toast.LENGTH_LONG)
            } finally {
                binding.swipeRefresh.isRefreshing = false
                binding.rvList.visibility = View.VISIBLE
            }
        }
    }

    private fun loadAdapter(){
        adapter = UseCfdiAdapter(listAdapterModel, this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    override fun onItemClick(
        v: View,
        position: Int,
        data: UseCfdiAdapterModel
    ) {

    }
}