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
    queryParams: Map<Int, String> = emptyMap(),
    navOptions: NavOptions? = null
) {
    val deeplinkUri = fragment.resources.getString(deeplinkResId)

    var deeplinkSb = deeplinkUri
    pathParams.forEach {
        val key = fragment.resources.getString(it.key)
        deeplinkSb = deeplinkSb.replace(Regex(escape("{$key}")), it.value)
    }

    if (queryParams.isNotEmpty()) {
        deeplinkSb = if (deeplinkSb.contains("?")) {
            deeplinkSb.substringBeforeLast("?").plus("?")
        } else {
            deeplinkSb.plus("?")
        }
        val queryParamsList = queryParams.toList()
        queryParamsList.forEachIndexed { index, pair ->
            val key = fragment.resources.getString(pair.first)
            deeplinkSb = deeplinkSb.plus("${key}=${pair.second}")
            if (index != queryParamsList.size - 1) {
                deeplinkSb = deeplinkSb.plus("&")
            }
        }
    }

    // queryParams.forEach {
    //     val key = fragment.resources.getString(it.key)
    //     deeplinkSb = deeplinkSb.replace(Regex(escape("{$key}")), it.value)
    // }

    val replacedDeeplinkUri = Uri.parse(deeplinkSb)
    val pathSegments = replacedDeeplinkUri.pathSegments
    if (pathSegments.any { it.startsWith("{") }) {
        throw IllegalArgumentException("Incorrect pathParams: Required: $pathSegments. Found: $pathParams")
    }

    fragment.findNavController().navigate(replacedDeeplinkUri, navOptions)
}
