package data.feature.flow.test.di

import core.lib.result.Result
import data.feature.flow.test.FakeStorage
import domain.flow.repositories.NodeRepository
import domain.flow.usecases.save.node.CreateNodeInput
import domain.models.flow.Node
import io.reactivex.Observable

class FakeNodeRepository : NodeRepository {

    override fun getAll(): Observable<Result<List<Node>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getNodes()))
    }

    override fun create(input: CreateNodeInput): Observable<Result<Node>> {
        return Observable.just(Result.OnSuccess(FakeStorage.createNode(input)))
    }
}
