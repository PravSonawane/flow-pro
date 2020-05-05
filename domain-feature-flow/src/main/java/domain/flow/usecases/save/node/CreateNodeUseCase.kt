package domain.flow.usecases.save.node

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable
import javax.inject.Inject

class CreateNodeUseCase @Inject constructor(
    private val nodeRepository: NodeRepository
) : ObservableResultUseCase<CreateNodeInput, Node> {

    override operator fun invoke(input: CreateNodeInput): Observable<Result<Node>> {
        return nodeRepository.create(input)
    }
}
