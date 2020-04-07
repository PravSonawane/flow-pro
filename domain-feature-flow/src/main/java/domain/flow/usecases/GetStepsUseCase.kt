package domain.flow.usecases

import core.lib.plugin.Plugin
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.models.flow.Step
import domain.models.flow.StepType
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class GetStepsUseCase @Inject constructor(
    @Named(GetAllStepsUseCase.NAMED)
    private val getAllStepsUseCase: BusinessUseCase<GetAllStepsInput, List<Step>>,
    @Named(GetCurrentInputStepsUseCase.NAMED)
    private val getCurrentInputStepsUseCase: BusinessUseCase<GetInputStepsInput, List<Step>>,
    @Named(GetCurrentOutputStepsUseCase.NAMED)
    private val getCurrentOutputStepsUseCase: BusinessUseCase<GetOutputStepsInput, List<Step>>
) : ObservableResultUseCase<GetStepsInput, List<Step>> {

    override fun invoke(input: GetStepsInput): Observable<Result<List<Step>>> {
        return Observable.just(input)
            .flatMap { getInputStepsByStepType(it) }
    }

    private fun getInputStepsByStepType(input: GetStepsInput): Observable<Result<List<Step>>> {
        return when {
            input.stepId == null -> getAllStepsUseCase(allStepsInput(input.flowId))
            input.stepType == StepType.INPUT -> getCurrentInputStepsUseCase(
                currentInputStepsInput(input.stepId)
            )
            input.stepType == StepType.OUTPUT -> getCurrentOutputStepsUseCase(
                currentOutputStepsInput(input.stepId)
            )
            else -> DomainError(INVALID_INPUT).toResult()
        }
    }

    private fun allStepsInput(flowId: String): BusinessData<GetAllStepsInput> {
        return BusinessData(
            "42ec4f7f-d032",
            Plugin("2d654511-3fa2"),
            GetAllStepsInput(flowId)
        )
    }

    private fun currentInputStepsInput(stepId: String): BusinessData<GetInputStepsInput> {
        return BusinessData(
            "d5869728-eded",
            Plugin("8ef2e492-f438"),
            GetInputStepsInput(stepId)
        )
    }

    private fun currentOutputStepsInput(stepId: String): BusinessData<GetOutputStepsInput> {
        return BusinessData(
            "ad2908cf-159d",
            Plugin("43ea3a44-768f"),
            GetOutputStepsInput(stepId)
        )
    }

    companion object {
        const val NAMED = "GET_STEPS"
        const val INVALID_INPUT = "fa53026f-e8a8"
    }
}

data class GetStepsInput(
    val flowId: String,
    val stepId: String? = null,
    val stepType: StepType? = null
)
