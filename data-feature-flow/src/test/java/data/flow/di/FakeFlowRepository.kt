package data.flow.di

import core.lib.result.Result
import data.flow.repositories.FakeStorage
import domain.flow.repositories.FlowRepository
import domain.flow.usecases.SaveOrUpdateFlowUseCase
import domain.models.flow.Flow
import io.reactivex.Observable

class FakeFlowRepository : FlowRepository {

    override fun get(id: String): Observable<Result<Flow>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getFlow()))
    }

    override fun save(input: SaveOrUpdateFlowUseCase.Input): Observable<Result<Flow>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getFlow()))
    }
}
