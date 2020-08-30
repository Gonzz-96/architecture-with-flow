package com.mx.gonz.flow.architecture.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.mx.gonz.flow.architecture.R
import com.mx.gonz.flow.architecture.adapter.PokemonAdapter
import com.mx.gonz.flow.architecture.databinding.ActivityMainBinding
import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        binding.rvPokemons.adapter = PokemonAdapter(emptyList())

        initializeListeners()
        observeData()
    }

    private fun initializeListeners() {
        binding.btnDropStorage.setOnClickListener { mainViewModel.resetStorage() }
        binding.btnGetRemotePokemons.setOnClickListener { mainViewModel.getRemotePokemons() }
    }

    private fun observeData() {
        mainViewModel.pokemons.observe(this) {
            (binding.rvPokemons.adapter as? PokemonAdapter)?.updateList(it)
        }
    }
}
