package ui.navigation

import android.net.Uri
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlin.text.Regex.Companion.escape

fun navigate(
    fragment: Fragment,
    @StringRes deeplinkResId: Int,
    pathParams: Map<String, String> = emptyMap(),
    queryParams: Map<String, String> = emptyMap(),
    navOptions: NavOptions? = null
) {
    val deeplinkUri = fragment.resources.getString(deeplinkResId)
    fragment.findNavController().navigate(
        replaceParams(deeplinkUri, pathParams),
        navOptions
    )
}

private fun replaceParams(
    deeplinkUri: String,
    pathParams: Map<String, String> = emptyMap(),
    queryParams: Map<String, String> = emptyMap()
): Uri {
    var deeplinkSb = deeplinkUri
    pathParams.forEach {
        deeplinkSb = deeplinkSb.replace(Regex(escape("{" + it.key + "}")), it.value)
    }
    val replacedDeeplinkUri = Uri.parse(deeplinkSb)
    val pathSegments = replacedDeeplinkUri.pathSegments
    if (pathSegments.any { it.startsWith("{") }) {
        throw IllegalArgumentException("Incorrect pathParams: Required: $pathSegments. Found: $pathParams")
    }
    return replacedDeeplinkUri
}
