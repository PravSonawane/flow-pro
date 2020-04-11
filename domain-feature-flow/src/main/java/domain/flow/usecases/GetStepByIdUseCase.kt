package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetStepByIdUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<String, Step> {

    override operator fun invoke(input: String): Observable<Result<Step>> {
        return stepRepository.getStepById(input)
    }

    companion object {
        const val NAMED = "GET_STEP_BY_ID"
        const val ANALYTICS_KEY = "98301cab-9995"
        const val PLUGIN_KEY = "8a35d450-f99b"
    }
}
