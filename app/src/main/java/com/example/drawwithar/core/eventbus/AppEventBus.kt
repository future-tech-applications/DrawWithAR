package com.example.drawwithar.core.eventbus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AppEventBus {

    // Private MutableSharedFlow for internal use
    private val _eventFlow = MutableSharedFlow<AppEvent>(extraBufferCapacity = 1)

    // Public SharedFlow to expose the events
    val eventFlow: SharedFlow<AppEvent> get() = _eventFlow

    /**
     * Subscribes to the events in the AppEventBus.
     * @param scope CoroutineScope in which the subscription will run
     * @param block A suspendable function to handle the events
     */
    fun subscribe(scope: CoroutineScope, block: suspend (AppEvent) -> Unit) {
        scope.launch {
            _eventFlow.collect { event ->
                block(event)
            }
        }
    }

    /**
     * Emits an event for all the subscribers.
     * @param appEvent The event to be emitted
     */
    suspend fun emit(appEvent: AppEvent) {
        _eventFlow.emit(appEvent)
    }
}
