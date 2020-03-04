package data.flow.di

import core.lib.result.Result
import data.flow.repositories.FakeStorage
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable

class FakeStepRepository : StepRepository {

    override fun getAll(flowId: String): Observable<Result<List<Step>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getFlow().steps!!))
    }

    override fun getCurrentInputSteps(stepId: String): Observable<Result<List<Step>>> {
        val step = FakeStorage.getFlow().steps?.find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step.inputSteps!!))
    }

    override fun getCurrentOutputSteps(stepId: String): Observable<Result<List<Step>>> {
        val step = FakeStorage.getFlow().steps?.find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step.outputSteps!!))
    }

    override fun getPossibleInputSteps(stepId: String): Observable<Result<List<Step>>> {
        val allSteps = FakeStorage.getFlow().steps!!
        val inputSteps = FakeStorage.getFlow().steps?.find { it.id == stepId }!!.inputSteps!!
        val possibleInputSteps = allSteps.minus(inputSteps)
        return Observable.just(Result.OnSuccess(possibleInputSteps))
    }

    override fun getPossibleOutputSteps(stepId: String): Observable<Result<List<Step>>> {
        val allSteps = FakeStorage.getFlow().steps!!
        val outputSteps = FakeStorage.getFlow().steps?.find { it.id == stepId }!!.outputSteps!!
        val possibleOutputSteps = allSteps.minus(outputSteps)
        return Observable.just(Result.OnSuccess(possibleOutputSteps))
    }

    override fun getStepById(stepId: String): Observable<Result<Step>> {
        val step = FakeStorage.getFlow().steps?.find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step))
    }
}
