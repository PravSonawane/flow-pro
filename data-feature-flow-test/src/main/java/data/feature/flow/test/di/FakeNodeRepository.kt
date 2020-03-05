package data.feature.flow.test.di

import core.lib.result.Result
import data.feature.flow.test.FakeStorage
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable

class FakeNodeRepository : NodeRepository {

    override fun getAll(): Observable<Result<List<Node>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getNodes()))
    }
}
