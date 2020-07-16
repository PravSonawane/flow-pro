package ui.lib.views.types

import androidx.annotation.StringRes

data class TextResourceBinding(
    val text: CharSequence? = null,
    @StringRes val textRes: Int? = null
)