package com.example.pokemontestapp.ui.pokemonDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokemontestapp.R
import com.example.pokemontestapp.databinding.FragmentPokemonDetailsBinding
import com.example.pokemontestapp.domain.entities.PokemonDetails
import com.example.pokemontestapp.ui.base.BaseFragment
import com.example.pokemontestapp.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment() : BaseFragment(R.layout.fragment_pokemon_details) {

    private lateinit var binding: FragmentPokemonDetailsBinding
    override val viewModel by viewModels<PokemonDetailsViewModel>()

    val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonDetailsBinding.bind(view)

        viewModel.getPokemonDetails(args.pokemonId)

        observeErrors()
        observeNavUp()

        viewModel.pokemonDetails.observe(viewLifecycleOwner) {
            drawPokemonDetails(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun drawPokemonDetails(pokemonDetails: PokemonDetails) {
        Glide.with(requireContext())
            .load(pokemonDetails.imageUrl)
            .placeholder(R.drawable.holder)
            .error(R.drawable.holder)
            .centerCrop()
            .into(binding.imageView)

        binding.tvName.text = pokemonDetails.name
        binding.tvHeight.text = "Height: ${pokemonDetails.height}"
        binding.tvWeight.text = "Weight: ${pokemonDetails.weight}"
        binding.tvTypesResult.text = pokemonDetails.types.toString()
    }

    private fun observeErrors() {
        viewModel.showErrorMessageEvent.observeEvent(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.showErrorMessageResEvent.observeEvent(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeNavUp() {
        viewModel.navigationUpAction.observeEvent(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }
}