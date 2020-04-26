package core.lib.experimentation.impl

import core.lib.experimentation.ExperimentationRepository
import core.lib.result.Result
import io.reactivex.Observable

class DefaultExperimentationRepository : ExperimentationRepository {
    override fun isEnabled(request: ExperimentationRepository.Request): Observable<Result<Boolean>> {
        return Observable.just(Result.OnSuccess(true))
    }
}
