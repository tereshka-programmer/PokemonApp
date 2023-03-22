package com.example.pokemontestapp.ui.pokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import com.example.pokemontestapp.ui.base.BaseViewModel
import com.example.pokemontestapp.utils.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : BaseViewModel() {

    val pokemonsFlow: Flow<PagingData<Pokemon>> = pokemonsRepository.getPokemons().cachedIn(viewModelScope)

}