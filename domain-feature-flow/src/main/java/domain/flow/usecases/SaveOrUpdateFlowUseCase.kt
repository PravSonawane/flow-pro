package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.models.SaveFlowInput
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

class SaveOrUpdateFlowUseCase @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<SaveFlowInput, Flow> {

    override operator fun invoke(input: SaveFlowInput): Observable<Result<Flow>> {
        return flowRepository.save(input)
    }
}
