package domain.flow.repositories

import core.lib.result.Result
import domain.models.flow.Node
import io.reactivex.Observable

interface NodeRepository {
    fun getAll(): Observable<Result<List<Node>>>
}