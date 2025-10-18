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
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCfdiActivity : AppCompatActivity() {

    private lateinit var binding: UsecfdiLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsecfdiLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}