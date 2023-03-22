package com.example.pokemontestapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemontestapp.R
import com.example.pokemontestapp.data.network.base.BackendException
import com.example.pokemontestapp.data.network.base.ConnectionException
import com.example.pokemontestapp.utils.MutableLiveEvent
import com.example.pokemontestapp.utils.publishEvent
import com.example.pokemontestapp.utils.share
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel(): ViewModel() {

    private val _showErrorMessageResEvent = MutableLiveEvent<Int>()
    val showErrorMessageResEvent = _showErrorMessageResEvent.share()

    private val _showErrorMessageEvent = MutableLiveEvent<String>()
    val showErrorMessageEvent = _showErrorMessageEvent.share()

    fun CoroutineScope.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block.invoke(this)
            } catch (e: ConnectionException) {
                _showErrorMessageResEvent.publishEvent(R.string.connection_error)
            } catch (e: BackendException) {
                _showErrorMessageEvent.publishEvent("Error: ${e.code}")
            } catch (e: Exception) {
                _showErrorMessageResEvent.publishEvent(R.string.internal_error)
            }
        }
    }

}