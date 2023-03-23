package com.example.pokemontestapp.ui.pokemonDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemontestapp.R
import com.example.pokemontestapp.data.network.base.BackendException
import com.example.pokemontestapp.data.network.base.ConnectionException
import com.example.pokemontestapp.domain.entities.PokemonDetails
import com.example.pokemontestapp.domain.repository.PokemonsLocalRepository
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import com.example.pokemontestapp.utils.MutableLiveEvent
import com.example.pokemontestapp.utils.MutableUnitLiveEvent
import com.example.pokemontestapp.utils.publishEvent
import com.example.pokemontestapp.utils.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository,
    private val pokemonsLocalRepository: PokemonsLocalRepository
): ViewModel(){

    private val _navigateUpAction = MutableUnitLiveEvent()
    val navigationUpAction = _navigateUpAction.share()

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails = _pokemonDetails.share()

    private val _showErrorMessageResEvent = MutableLiveEvent<Int>()
    val showErrorMessageResEvent = _showErrorMessageResEvent.share()

    private val _showErrorMessageEvent = MutableLiveEvent<String>()
    val showErrorMessageEvent = _showErrorMessageEvent.share()

    fun getPokemonDetails(pokemonId: Long) {
        viewModelScope.safeLaunch(pokemonId) {
            _pokemonDetails.value = pokemonsRepository.getPokemonDetails(pokemonId)
        }
    }

    fun CoroutineScope.safeLaunch(id: Long, block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block.invoke(this)
            } catch (e: ConnectionException) {
                _showErrorMessageResEvent.publishEvent(R.string.connection_error)
                try {
                    _pokemonDetails.value = pokemonsLocalRepository.getPokemonDetails(id)
                } catch (e: Exception) {
                    _showErrorMessageResEvent.publishEvent(R.string.storage_error)
                    _navigateUpAction.publishEvent()
                }
            } catch (e: BackendException) {
                _showErrorMessageEvent.publishEvent("Error: ${e.code}")
                _navigateUpAction.publishEvent()
            } catch (e: Exception) {
                _showErrorMessageResEvent.publishEvent(R.string.internal_error)
                _navigateUpAction.publishEvent()
            }
        }
    }
}