package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetPossibleInputStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetPossibleInputStepsInput, List<Step>> {

    override fun invoke(input: GetPossibleInputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getPossibleInputSteps(input.stepId)
    }

    companion object {
        const val NAMED = "GET_POSSIBLE_INPUT_STEPS"
    }
}
data class GetPossibleInputStepsInput(
    val stepId: String
)
