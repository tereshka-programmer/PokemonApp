package com.example.pokemontestapp.ui.pokemons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemontestapp.R
import com.example.pokemontestapp.databinding.FragmentPokemonsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonsFragment() : Fragment(R.layout.fragment_pokemons) {

    private lateinit var binding: FragmentPokemonsBinding

    private val viewModel by viewModels<PokemonsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonsBinding.bind(view)

        setupPokemonsList()
    }

    private fun setupPokemonsList() {
        val adapter = PokemonsAdapter( object : OnItemClickListener {
            override fun navigateToPokemonDetails(id: Long) {
                navigateToDetails(id)
            }
        })
        val tryAgainAction: TryAgainAction = { adapter.retry() }
        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)
        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        binding.rcvPokemons.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvPokemons.adapter = adapterWithLoadState

        observePokemons(adapter)
    }

    private fun observePokemons(adapter: PokemonsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonsFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun navigateToDetails(pokemonId: Long) {
        val direction = PokemonsFragmentDirections.actionPokemonsFragmentToPokemonDetailsFragment(pokemonId)
        findNavController().navigate(direction)
    }

}