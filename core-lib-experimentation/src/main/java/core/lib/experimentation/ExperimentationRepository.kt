package core.lib.experimentation

import core.lib.result.Result
import io.reactivex.Observable

interface ExperimentationRepository {
    fun isEnabled(request: Request): Observable<Result<Boolean>>

    data class Request(
        val experimentKey: String
    )
}
