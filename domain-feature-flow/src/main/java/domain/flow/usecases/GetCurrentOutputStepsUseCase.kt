package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrentOutputStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetOutputStepsInput, List<Step>> {

    override fun invoke(input: GetOutputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getOutputSteps(input.stepId)
    }

    companion object {
        const val NAMED = "GET_CURRENT_OUTPUT_STEPS"
    }
}

data class GetOutputStepsInput(
    val stepId: String
)
