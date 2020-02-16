package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetAllStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetAllStepsUseCase.Input, List<Step>> {

    override fun invoke(input: Input): Observable<Result<List<Step>>> {
        return stepRepository.getAll(input.flowId)
    }

    data class Input(
        val flowId: String
    )
}
