package ui.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavOptions

data class SimpleNavigationConfig(
    @StringRes val deeplinkResId: Int,
    val pathParams: Map<Int, String> = emptyMap(),
    val queryParams: Map<Int, String> = emptyMap(),
    val navOptions: NavOptions? = null
) : NavigationConfig