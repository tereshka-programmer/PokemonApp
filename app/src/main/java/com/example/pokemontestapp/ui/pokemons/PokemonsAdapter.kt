package com.example.pokemontestapp.ui.pokemons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemontestapp.databinding.ItemPokemonBinding
import com.example.pokemontestapp.domain.entities.Pokemon

interface OnItemClickListener {

    fun navigateToPokemonDetails(id: Long)

}

class PokemonsAdapter(
    private val listener: OnItemClickListener
): PagingDataAdapter<Pokemon, PokemonsAdapter.PokemonsViewHolder>(PokemonsDiffCallback()), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

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

    override fun onClick(view: View?) {
        val pokemon = view?.tag as Pokemon
        listener.navigateToPokemonDetails(pokemon.id)
    }
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