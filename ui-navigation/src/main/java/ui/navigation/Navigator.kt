package ui.navigation

import android.app.Activity
import android.net.Uri
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import app.di.annotations.ActivityContext
import javax.inject.Inject
import kotlin.text.Regex.Companion.escape

class Navigator @Inject constructor(
    @ActivityContext private val activity: Activity,
    @IntegerRes private val navHostViewId: Int
) {

    fun navigate(
        @StringRes deeplinkResId: Int,
        pathParams: Map<Int, String> = emptyMap(),
        queryParams: Map<Int, String> = emptyMap(),
        navOptions: NavOptions? = null
    ) {
        val deeplinkUri = activity.resources.getString(deeplinkResId)

        var deeplinkSb = deeplinkUri
        pathParams.forEach {
            val key = activity.resources.getString(it.key)
            deeplinkSb = deeplinkSb.replace(Regex(escape("{$key}")), it.value)
        }

        deeplinkSb = if (deeplinkSb.contains("?")) {
            deeplinkSb.substringBeforeLast("?").plus("?")
        } else {
            deeplinkSb.plus("?")
        }
        val queryParamsList = queryParams.toList()
        queryParamsList.forEachIndexed { index, pair ->
            val key = activity.resources.getString(pair.first)
            deeplinkSb = deeplinkSb.plus("$key=${pair.second}")
            if (index != queryParamsList.size - 1) {
                deeplinkSb = deeplinkSb.plus("&")
            }
        }

        val replacedDeeplinkUri = Uri.parse(deeplinkSb)
        val pathSegments = replacedDeeplinkUri.pathSegments
        if (pathSegments.any { it.startsWith("{") }) {
            throw IllegalArgumentException("Incorrect pathParams: Required: $pathSegments. Found: $pathParams")
        }

        activity.findNavController(navHostViewId).navigate(replacedDeeplinkUri, navOptions)
    }
}
