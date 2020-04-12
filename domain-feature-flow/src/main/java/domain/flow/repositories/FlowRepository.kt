package domain.flow.repositories

import core.lib.result.Result
import domain.flow.usecases.save.flow.SaveOrUpdateFlowUseCase
import domain.models.flow.Flow
import io.reactivex.Observable

interface FlowRepository {
    fun getAll(): Observable<Result<List<Flow>>>
    fun get(id: String): Observable<Result<Flow>>
    fun save(input: SaveOrUpdateFlowUseCase.Input): Observable<Result<Flow>>
}
