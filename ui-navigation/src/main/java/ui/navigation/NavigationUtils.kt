package ui.navigation

import android.net.Uri
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlin.text.Regex.Companion.escape

fun navigate(
    @StringRes deeplinkResId: Int,
    fragment: Fragment,
    navOptions: NavOptions? = null,
    pathParams: Map<String, String> = emptyMap(),
    queryParams: Map<String, String> = emptyMap()
) {
    val deeplinkUri = fragment.resources.getString(deeplinkResId)
    fragment.findNavController().navigate(
        replacePathParams(deeplinkUri, pathParams),
        navOptions
    )
}

private fun replacePathParams(deeplinkUri: String, pathParams: Map<String, String> = emptyMap()): Uri {
    var deeplinkSb = deeplinkUri
    pathParams.forEach {
        deeplinkSb = deeplinkSb.replace(Regex(escape("{" + it.key + "}")), it.value)
    }
    val replacedDeeplinkUri = Uri.parse(deeplinkSb)
    if (replacedDeeplinkUri.pathSegments.any { it.startsWith("{") }) {
        throw IllegalArgumentException("Incorrect pathParams: $pathParams")
    }
    return replacedDeeplinkUri
}
