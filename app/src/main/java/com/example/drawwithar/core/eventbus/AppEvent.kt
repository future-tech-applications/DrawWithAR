package com.example.drawwithar.core.eventbus

sealed class AppEvent {

    // custom data class or data objects for specific events
    data class FlashLightToggledEvent(val toggledValue: Boolean) : AppEvent()
}
