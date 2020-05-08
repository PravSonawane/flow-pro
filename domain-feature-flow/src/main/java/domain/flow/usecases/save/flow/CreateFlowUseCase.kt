package domain.flow.usecases.save.flow

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

typealias CreateFlowUseCase = ObservableResultUseCase<CreateFlowInput, Flow>

class CreateFlowUseCaseInternal @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<CreateFlowInput, Flow> {

    override operator fun invoke(input: CreateFlowInput): Observable<Result<Flow>> {
        return flowRepository.create(input)
    }
}
