package domain.flow.repositories

import core.lib.result.Result
import domain.models.flow.Step
import io.reactivex.Observable

interface StepRepository {
    fun getAll(flowId: String): Observable<Result<List<Step>>>
    fun getCurrentInputSteps(stepId: String): Observable<Result<List<Step>>>
    fun getCurrentOutputSteps(stepId: String): Observable<Result<List<Step>>>
    fun getPossibleInputSteps(stepId: String): Observable<Result<List<Step>>>
    fun getPossibleOutputSteps(stepId: String): Observable<Result<List<Step>>>
    fun getStepById(stepId: String): Observable<Result<Step>>
}
