package domain.flow.usecases.save.node

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.NodeRepository
import domain.models.flow.Node
import io.reactivex.Observable
import javax.inject.Inject

typealias CreateNodeUseCase = ObservableResultUseCase<CreateNodeInput, Node>

class CreateNodeFunction @Inject constructor(
    private val nodeRepository: NodeRepository
) : CreateNodeUseCase {

    override operator fun invoke(input: CreateNodeInput): Observable<Result<Node>> {
        return nodeRepository.create(input)
    }

    companion object {
        const val ANALYTICS_KEY = "74e4023f-02fb"
        const val PLUGIN_KEY = "bf12f1ce-80d8"
    }
}
