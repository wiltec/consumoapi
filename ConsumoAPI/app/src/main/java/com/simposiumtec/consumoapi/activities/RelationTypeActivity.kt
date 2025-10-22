package com.simposiumtec.consumoapi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simposiumtec.consumoapi.databinding.RelationTypeLayoutBinding

class RelationTypeActivity : AppCompatActivity() {

    private lateinit var binding: RelationTypeLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RelationTypeLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}