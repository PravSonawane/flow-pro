package core.lib.experimentation.test.di

import core.lib.experimentation.ExperimentationRepository
import core.lib.result.Result
import io.reactivex.Observable

class FakeExperimentationRepository : ExperimentationRepository {

    override fun isEnabled(request: ExperimentationRepository.Request): Observable<Result<Boolean>> {
        return Observable.just(Result.OnSuccess(true))
    }
}
