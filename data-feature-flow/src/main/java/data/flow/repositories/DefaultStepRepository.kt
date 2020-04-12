package data.flow.repositories

import core.lib.result.Result
import domain.flow.repositories.StepRepository
import domain.flow.usecases.save.step.CreateStepInput
import domain.models.flow.Step
import io.reactivex.Observable

class DefaultStepRepository : StepRepository {

    override fun getAll(flowId: String): Observable<Result<List<Step>>> {
        return Observable.just(Result.OnSuccess(FakeStorage.getFlow(flowId).steps ?: emptyList()))
    }

    override fun getCurrentInputSteps(stepId: String): Observable<Result<List<Step>>> {
        val step = FakeStorage.getAllSteps()
            .find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step.inputSteps ?: emptyList()))
    }

    override fun getCurrentOutputSteps(stepId: String): Observable<Result<List<Step>>> {
        val step = FakeStorage.getAllSteps()
            .find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step.outputSteps ?: emptyList()))
    }

    override fun getPossibleInputSteps(stepId: String): Observable<Result<List<Step>>> {
        val allSteps = FakeStorage.getAllSteps()
        val inputSteps = FakeStorage.getAllSteps()
            .find { it.id == stepId }!!
            .inputSteps ?: emptyList()
        val inputStepIds = inputSteps.map { it.id }
        val possibleInputSteps = allSteps.filter { it.id !in inputStepIds }
        return Observable.just(Result.OnSuccess(possibleInputSteps))
    }

    override fun getPossibleOutputSteps(stepId: String): Observable<Result<List<Step>>> {
        val allSteps = FakeStorage.getAllSteps()
        val outputSteps = FakeStorage.getAllSteps()
            .find { it.id == stepId }!!
            .outputSteps ?: emptyList()
        val outputStepIds = outputSteps.map { it.id }
        val possibleOutputSteps = allSteps.filter { it.id !in outputStepIds }
        return Observable.just(Result.OnSuccess(possibleOutputSteps))
    }

    override fun getStepById(stepId: String): Observable<Result<Step>> {
        val step = FakeStorage.getAllSteps()
            .find { it.id == stepId }!!
        return Observable.just(Result.OnSuccess(step))
    }

    override fun create(input: CreateStepInput): Observable<Result<Step>> {
        return Observable.just(Result.OnSuccess(FakeStorage.createStep(input)))
    }
}
