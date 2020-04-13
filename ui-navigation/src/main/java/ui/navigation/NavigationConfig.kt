package ui.navigation

import androidx.navigation.NavOptions
import domain.models.core.Mappable

open class NavigationConfig(
    open val deeplinkResId: Int,
    open val pathParams: Map<Int, String> = emptyMap(),
    open val queryParams: Map<Int, String> = emptyMap(),
    open val navOptions: NavOptions? = null
) : Mappable {

    override fun toMap(): Map<String, Any?> {
        return mapper()
    }
}

fun NavigationConfig.mapper(): Map<String, Any?> {
    val map = LinkedHashMap<String, Any?>()
    map["deeplinkResId"] = deeplinkResId
    map["pathParams"] = pathParams
    map["queryParams"] = queryParams
    map["navOptions"] = navOptions
    return map
}
