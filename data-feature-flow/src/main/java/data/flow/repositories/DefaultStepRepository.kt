package data.flow.repositories

import core.lib.result.Result
import domain.flow.repositories.StepRepository
import domain.models.flow.Node
import domain.models.flow.Step
import io.reactivex.Observable

class DefaultStepRepository : StepRepository {

    override fun getAll(flowId: String): Observable<Result<List<Step>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getFlow().steps!!))
    }

    override fun getInputSteps(stepId: String): Observable<Result<List<Step>>> {
        val step = FakeStorage.getFlow().steps?.find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step.inputSteps!!))
    }
}
