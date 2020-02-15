package domain.flow.usecases

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
}
