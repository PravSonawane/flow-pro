package domain.flow.usecases.get.step

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

typealias GetCurrentOutputStepsUseCase = ObservableResultUseCase<GetOutputStepsInput, List<Step>>

class GetCurrentOutputStepsFunction @Inject constructor(
    private val stepRepository: StepRepository
) : GetCurrentOutputStepsUseCase {

    override fun invoke(input: GetOutputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getCurrentOutputSteps(input.stepId)
    }

    companion object {
        const val ANALYTICS_KEY = "ad2908cf-159d"
        const val PLUGIN_KEY = "43ea3a44-768f"
    }
}

data class GetOutputStepsInput(
    val stepId: String
)
