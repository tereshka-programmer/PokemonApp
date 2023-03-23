package com.example.pokemontestapp.ui.pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : ViewModel() {

    val pokemonsFlow: Flow<PagingData<Pokemon>> = pokemonsRepository.getPokemons().cachedIn(viewModelScope)

}