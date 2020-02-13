package domain.flow.usecases

import core.lib.usecase.ObservableResultUseCase
import domain.core.result.Result
import domain.flow.models.Flow
import domain.flow.models.SaveFlowInput
import domain.flow.repositories.FlowRepository
import io.reactivex.Observable
import javax.inject.Inject

class SaveOrUpdateFlowUseCase @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<SaveFlowInput, Flow> {

    override operator fun invoke(input: SaveFlowInput): Observable<Result<Flow>> {
        return flowRepository.save(input)
    }
}