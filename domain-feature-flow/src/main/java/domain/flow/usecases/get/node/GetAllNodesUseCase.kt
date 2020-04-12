package domain.flow.usecases.get.node

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable
import javax.inject.Inject

class GetAllNodesUseCase @Inject constructor(
    private val nodeRepository: NodeRepository
) : ObservableResultUseCase<Unit, List<Node>> {

    override fun invoke(input: Unit): Observable<Result<List<Node>>> {
        return nodeRepository.getAll()
    }

    companion object {
        const val NAMED = "GET_ALL_NODES"
        const val ANALYTICS_KEY = "6254dd8c-f492"
        const val PLUGIN_KEY = "61f62f8c-28c5"
    }
}
