package domain.flow.repositories

import core.lib.result.Result
import domain.flow.usecases.save.flow.CreateFlowInput
import domain.flow.usecases.save.flow.CreateFlowUseCase
import domain.models.flow.Flow
import io.reactivex.Observable

interface FlowRepository {
    fun getAll(): Observable<Result<List<Flow>>>
    fun get(id: String): Observable<Result<Flow>>
    fun create(input: CreateFlowInput): Observable<Result<Flow>>
}
