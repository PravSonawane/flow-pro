package domain.flow.repositories

import core.lib.result.Result
import domain.flow.usecases.GetAllStepsUseCase
import domain.models.flow.Step
import io.reactivex.Observable

interface StepRepository {
    fun getAll(flowId: String): Observable<Result<List<Step>>>
}
