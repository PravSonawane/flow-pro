package domain.flow.usecases

import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import domain.models.flow.Step
import domain.models.flow.StepType
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class GetStepsUseCase @Inject constructor(
    @Named(GetAllStepsUseCase.NAMED)
    private val getAllStepsUseCase: ObservableResultUseCase<GetAllStepsInput, List<Step>>,
    @Named(GetCurrentInputStepsUseCase.NAMED)
    private val getCurrentInputStepsUseCase: ObservableResultUseCase<GetInputStepsInput, List<Step>>,
    @Named(GetCurrentOutputStepsUseCase.NAMED)
    private val getCurrentOutputStepsUseCase: ObservableResultUseCase<GetOutputStepsInput, List<Step>>
) : ObservableResultUseCase<GetStepsInput, List<Step>> {

    override fun invoke(input: GetStepsInput): Observable<Result<List<Step>>> {
        return Observable.just(input)
            .flatMap { getInputStepsByStepType(it) }
    }

    private fun getInputStepsByStepType(input: GetStepsInput): Observable<Result<List<Step>>> {
        return when {
            input.stepId == null -> getAllStepsUseCase(GetAllStepsInput(input.flowId))
            input.stepType == StepType.INPUT -> getCurrentInputStepsUseCase(GetInputStepsInput(input.stepId))
            input.stepType == StepType.OUTPUT -> getCurrentOutputStepsUseCase(GetOutputStepsInput(input.stepId))
            else -> invalidInputError(input).toResult()
        }
    }

    private fun invalidInputError(input: GetStepsInput): DomainError {
        return DomainError("fa53026f-e8a8", "Cannot handle input:$input")
    }

    companion object {
        const val NAMED = "GET_STEPS"
        const val ANALYTICS_KEY = "41508dfb-95b4"
        const val PLUGIN_KEY = "23145985-698d"
    }
}

data class GetStepsInput(
    val flowId: String,
    val stepId: String? = null,
    val stepType: StepType? = null
)
