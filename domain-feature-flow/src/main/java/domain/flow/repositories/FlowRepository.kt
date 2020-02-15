package domain.flow.repositories

import core.lib.result.Result
import domain.flow.models.SaveFlowInput
import domain.models.flow.Flow
import io.reactivex.Observable

interface FlowRepository {
    fun get(id: String): Observable<Result<Flow>>
    fun save(input: SaveFlowInput): Observable<Result<Flow>>
}
