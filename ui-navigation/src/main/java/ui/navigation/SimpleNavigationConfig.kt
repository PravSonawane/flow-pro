package ui.navigation

import androidx.navigation.NavOptions

class SimpleNavigationConfig(
    deeplinkResId: Int,
    pathParams: Map<Int, String> = emptyMap(),
    queryParams: Map<Int, String> = emptyMap(),
    navOptions: NavOptions? = null
) : NavigationConfig(deeplinkResId, pathParams, queryParams, navOptions)
