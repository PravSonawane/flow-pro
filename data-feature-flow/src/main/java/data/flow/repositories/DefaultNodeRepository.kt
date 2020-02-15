package data.flow.repositories

import core.lib.result.Result
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable

class DefaultNodeRepository : NodeRepository {

    private val nodes: MutableList<Node> = mutableListOf()

    init {
        nodes.add(Node("1", "Start Node"))
        nodes.add(Node("2", "Process Node"))
        nodes.add(Node("3", "Decision Node"))
        nodes.add(Node("4", "Stop Node"))
    }

    override fun getAll(): Observable<Result<List<Node>>> {
        return Observable.just(Result.OnSuccess(nodes))
    }
}
