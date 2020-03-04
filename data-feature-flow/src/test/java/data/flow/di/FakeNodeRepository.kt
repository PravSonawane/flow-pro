package data.flow.di

import core.lib.result.Result
import data.flow.repositories.FakeStorage
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable

class FakeNodeRepository : NodeRepository {

    override fun getAll(): Observable<Result<List<Node>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getNodes()))
    }
}
