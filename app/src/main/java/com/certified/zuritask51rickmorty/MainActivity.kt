package com.certified.zuritask51rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.certified.zuritask51rickmorty.api.Api
import com.certified.zuritask51rickmorty.api.ApiService
import com.certified.zuritask51rickmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = Api.apiService
        val viewModelFactory = CharacterViewModelFactory(apiService)
        val viewModel: CharacterViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)
        }

        viewModel.showProgressBar.observe(this) {
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(this)

        val adapter = CharacterAdapter()
        binding.recyclerViewCharacters.adapter = adapter
    }
}