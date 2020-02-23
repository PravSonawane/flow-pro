package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetInputStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetInputStepsInput, List<Step>> {

    override fun invoke(input: GetInputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getInputSteps(input.stepId)
    }

    companion object {
        const val NAMED = "GET_INPUT_STEPS"
    }
}
data class GetInputStepsInput(
    val stepId: String
)
