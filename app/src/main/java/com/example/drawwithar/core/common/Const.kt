package com.example.drawwithar.core.common

object Const {
    object Flip {
        const val HORIZONTAL_SCALE = -1f
        const val VERTICAL_SCALE = -1f
        const val DEFAULT_SCALE = 1f
    }

    object Scale {
        const val MIN_SCALE = 0.5f
        const val MAX_SCALE = 2f
        const val INITIAL_SCALE = 1f
    }

    object Rotation {
        const val INITIAL_ROTATION = 0f
    }

    object Offset {
        const val INITIAL_OFFSET_X = 0f
        const val INITIAL_OFFSET_Y = 0f
        const val MAX_OFFSET_X = 150f
        const val MIN_OFFSET_X = -150f
        const val MAX_OFFSET_Y = 250f
        const val MIN_OFFSET_Y = -250f
    }

    object Drag {
        const val DAMPING_FACTOR = 0.2f
    }

    object OpacitySlider {
        const val INITIAL_VALUE = 0.5f
        const val MIN_VALUE = 0.1f
        const val MAX_VALUE = 1.0f
        const val IS_VISIBLE = true
    }
}
