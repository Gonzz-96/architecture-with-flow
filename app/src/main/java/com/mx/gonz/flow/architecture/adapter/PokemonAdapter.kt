package com.mx.gonz.flow.architecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.gonz.flow.architecture.databinding.ItemPokemonBinding
import com.mx.gonz.flow.architecture.domain.entity.Pokemon

class PokemonAdapter(
    listOfPokemons: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    var pokemons = listOfPokemons

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .let { inflater -> ItemPokemonBinding.inflate(inflater, parent, false) }
            .let { binding  -> ViewHolder(binding) }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(pokemons[position])

    override fun getItemCount(): Int =
        pokemons.size

    fun updateList(newPokemons: List<Pokemon>) {
        pokemons = newPokemons
        notifyDataSetChanged()
    }

    class ViewHolder(
        val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.tvPokemonId.text = pokemon.id.toString()
            binding.tvPokemonName.text = pokemon.name
        }
    }
}
