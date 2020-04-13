package ui.navigation

import android.app.Activity
import android.net.Uri
import androidx.annotation.IntegerRes
import androidx.navigation.findNavController
import app.di.annotations.ActivityContext
import core.lib.usecase.ObservableResultUseCase
import ui.navigation.usecases.LogNavigationUseCase
import javax.inject.Inject
import javax.inject.Named
import kotlin.text.Regex.Companion.escape

class SimpleNavigator @Inject constructor(
    @ActivityContext private val activity: Activity,
    @IntegerRes private val navHostViewId: Int,
    @Named(LogNavigationUseCase.NAMED)
    private val logNavigationUseCase: ObservableResultUseCase<NavigationConfig, NavigationConfig>
) : Navigator {

    override fun navigate(config: NavigationConfig) {
        require(config is SimpleNavigationConfig) {
            throw IllegalArgumentException("Required SimpleNavigationConfig. found:$config")
        }
        val deeplinkUri = activity.resources.getString(config.deeplinkResId)

        var deeplinkSb = deeplinkUri
        config.pathParams.forEach {
            val key = activity.resources.getString(it.key)
            deeplinkSb = deeplinkSb.replace(Regex(escape("{$key}")), it.value)
        }

        deeplinkSb = if (deeplinkSb.contains("?")) {
            deeplinkSb.substringBeforeLast("?").plus("?")
        } else {
            deeplinkSb.plus("?")
        }
        val queryParamsList = config.queryParams.toList()
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
            throw IllegalArgumentException("Incorrect pathParams: Required: $pathSegments. Found: ${config.pathParams}")
        }

        logNavigationUseCase(config).subscribe()
        activity.findNavController(navHostViewId).navigate(replacedDeeplinkUri, config.navOptions)
    }
}
