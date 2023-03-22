package com.example.pokemontestapp.ui.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemontestapp.databinding.ItemPokemonBinding
import com.example.pokemontestapp.domain.entities.Pokemon

class PokemonsAdapter(): PagingDataAdapter<Pokemon, PokemonsAdapter.PokemonsViewHolder>(PokemonsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)

        return PokemonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        val pokemon = getItem(position) ?: return
        holder.itemView.tag = pokemon

        with(holder.binding) {
            tvPokemonName.text = pokemon.name
        }
    }

    class PokemonsViewHolder(
        val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

// ---

class PokemonsDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

}