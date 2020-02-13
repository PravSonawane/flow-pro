package domain.flow.repositories

import domain.flow.models.Flow
import domain.flow.models.SaveFlowInput
import domain.core.result.Result
import io.reactivex.Observable

interface FlowRepository {
    fun get(id: String): Observable<Result<Flow>>
    fun save(input: SaveFlowInput): Observable<Result<Flow>>
}