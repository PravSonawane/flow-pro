package domain.flow.repositories

import core.lib.result.Result
import domain.flow.usecases.SaveOrUpdateFlowUseCase
import domain.models.flow.Flow
import io.reactivex.Observable

interface FlowRepository {
    fun get(id: String): Observable<Result<Flow>>
    fun save(input: SaveOrUpdateFlowUseCase.Input): Observable<Result<Flow>>
}
