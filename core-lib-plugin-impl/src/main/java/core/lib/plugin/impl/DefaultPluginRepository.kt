package core.lib.plugin.impl

import core.lib.plugin.Plugin
import core.lib.plugin.PluginRepository
import core.lib.result.Result
import io.reactivex.Observable

class DefaultPluginRepository : PluginRepository {

    override fun isEnabled(plugin: Plugin): Observable<Result<Boolean>> {
        return Observable.just(Result.OnSuccess(true))
    }
}