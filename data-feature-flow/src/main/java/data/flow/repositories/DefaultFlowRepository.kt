package data.flow.repositories

import core.lib.result.Result
import domain.flow.repositories.FlowRepository
import domain.flow.usecases.save.flow.CreateFlowInput
import domain.models.flow.Flow
import io.reactivex.Observable

class DefaultFlowRepository : FlowRepository {
    override fun getAll(): Observable<Result<List<Flow>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getAllFlows()))
    }

    override fun get(id: String): Observable<Result<Flow>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getFlow(id)))
    }

    override fun create(input: CreateFlowInput): Observable<Result<Flow>> {
        return Observable.just(Result.OnSuccess(FakeStorage.createFlow(input)))
    }
}
