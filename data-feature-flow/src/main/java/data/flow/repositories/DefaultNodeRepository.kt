package data.flow.repositories

import core.lib.result.Result
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable

class DefaultNodeRepository : NodeRepository {

    override fun getAll(): Observable<Result<List<Node>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getNodes()))
    }
}
