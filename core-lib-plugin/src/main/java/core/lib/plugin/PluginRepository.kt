package core.lib.plugin

import core.lib.result.Result
import io.reactivex.Observable

interface PluginRepository {
    fun isEnabled(plugin: Plugin): Observable<Result<Boolean>>
}