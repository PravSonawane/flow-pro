package domain.flow.repositories

import core.lib.result.Result
import domain.flow.usecases.save.node.CreateNodeInput
import domain.models.flow.Node
import io.reactivex.Observable

interface NodeRepository {
    fun getAll(): Observable<Result<List<Node>>>
    fun create(input: CreateNodeInput): Observable<Result<Node>>
}
