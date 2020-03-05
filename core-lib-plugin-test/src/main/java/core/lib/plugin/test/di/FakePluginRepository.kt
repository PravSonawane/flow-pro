package core.lib.plugin.test.di

import core.lib.plugin.Plugin
import core.lib.plugin.PluginRepository
import core.lib.result.Result
import io.reactivex.Observable

class FakePluginRepository : PluginRepository {

    override fun isEnabled(plugin: Plugin): Observable<Result<Boolean>> {
        return Observable.just(Result.OnSuccess(true))
    }
}
