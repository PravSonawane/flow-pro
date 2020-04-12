package domain.flow.usecases.save.flow

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

class SaveOrUpdateFlowUseCase @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<SaveOrUpdateFlowUseCase.Input, Flow> {

    override operator fun invoke(input: Input): Observable<Result<Flow>> {
        return flowRepository.save(input)
    }

    data class Input(
        val name: String? = null,
        val description: String? = null
    )
}
