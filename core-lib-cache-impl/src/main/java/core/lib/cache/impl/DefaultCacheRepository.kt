package core.lib.cache.impl

import core.lib.cache.CacheRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class DefaultCacheRepository : CacheRepository<String, Any> {

    val map: MutableMap<String, Any?> = HashMap()
    val cacheStream: Subject<Pair<String, Any?>> = PublishSubject.create()

    override fun cache(key: String, data: Any?): Maybe<Any> {
        map[key] = data
        return if (data == null) {
            Maybe.empty()
        } else {
            Maybe.just(data)
        }
    }

    override fun get(key: String): Observable<Any> {
        val cached = map[key]
        val filter = cacheStream.filter { it.first == key }.map { it.second }
        if (cached != null) {
            return Observable.just(cached).concatWith(filter)
        }
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
