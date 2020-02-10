package domain.flow.repositories

import domain.flow.models.Flow
import domain.flow.models.SaveFlowRequest
import domain.core.Result
import io.reactivex.Observable

interface FlowRepository {
    fun get(id: String): Observable<Result<Flow>>
    fun save(request: SaveFlowRequest): Observable<Result<Flow>>
}