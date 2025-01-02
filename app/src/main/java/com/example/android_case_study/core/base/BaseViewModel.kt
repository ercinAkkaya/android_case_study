package com.example.android_case_study.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface UIState
interface UIAction
interface UIEffect

abstract class BaseViewModel<Action : UIAction, State : UIState, Effect : UIEffect> : ViewModel() {

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<Action>()
    val events: SharedFlow<Action> = _events.asSharedFlow()

    private val _effects = Channel<Effect>(Channel.BUFFERED)
    val effects: Flow<Effect> = _effects.receiveAsFlow()

    init {
        subscribeToEvents()
        loadInitialData()
    }

    protected abstract fun createInitialState(): State

    private fun loadInitialData() {}

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _events.collect { event ->
                handleEvent(event)
            }
        }
    }

    fun setAction(event: Action) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }

    protected fun setState(reduce: State.() -> State) {
        _uiState.value = uiState.value.reduce()
    }

    protected fun setEffect(createEffect: () -> Effect) {
        val effect = createEffect()
        viewModelScope.launch {
            _effects.send(effect)
        }
    }

    protected abstract fun handleEvent(event: Action)
}