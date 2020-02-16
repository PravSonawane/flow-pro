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
    pathParams: Map<Int, String> = emptyMap(),
    queryParams: Map<String, String> = emptyMap(),
    navOptions: NavOptions? = null
) {
    val deeplinkUri = fragment.resources.getString(deeplinkResId)
    fragment.findNavController().navigate(
        replaceParams(fragment, deeplinkUri, pathParams),
        navOptions
    )
}

private fun replaceParams(
    fragment: Fragment,
    deeplinkUri: String,
    pathParams: Map<Int, String> = emptyMap(),
    queryParams: Map<Int, String> = emptyMap()
): Uri {
    var deeplinkSb = deeplinkUri
    pathParams.forEach {
        val key = fragment.resources.getString(it.key)
        deeplinkSb = deeplinkSb.replace(Regex(escape("{$key}")), it.value)
    }
    val replacedDeeplinkUri = Uri.parse(deeplinkSb)
    val pathSegments = replacedDeeplinkUri.pathSegments
    if (pathSegments.any { it.startsWith("{") }) {
        throw IllegalArgumentException("Incorrect pathParams: Required: $pathSegments. Found: $pathParams")
    }
    return replacedDeeplinkUri
}
