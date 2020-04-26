package core.lib.cache

import io.reactivex.Maybe
import io.reactivex.Observable

interface CacheRepository<Key, Data> {

    fun cache(key: Key, data: Data?): Maybe<Data>

    fun get(key: Key): Observable<Data>
}
