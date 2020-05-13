package domain.flow.usecases.get.step

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

typealias GetCurrentInputStepsUseCase = ObservableResultUseCase<GetInputStepsInput, List<Step>>

class GetCurrentInputStepsFunction @Inject constructor(
    private val stepRepository: StepRepository
) : GetCurrentInputStepsUseCase {

    override fun invoke(input: GetInputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getCurrentInputSteps(input.stepId)
    }

    companion object {
        const val ANALYTICS_KEY = "d5869728-eded"
        const val PLUGIN_KEY = "8ef2e492-f438"
    }
}
data class GetInputStepsInput(
    val stepId: String
)
