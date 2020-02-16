package data.flow.repositories

import core.lib.result.Result
import domain.flow.repositories.StepRepository
import domain.models.flow.Node
import domain.models.flow.Step
import io.reactivex.Observable

class DefaultStepRepository : StepRepository {

    private val nodes: MutableList<Node> = mutableListOf()
    private val steps: MutableList<Step> = mutableListOf()

    init {
        nodes.add(Node("1", "Start Node"))
        nodes.add(Node("2", "Process Node"))
        nodes.add(Node("3", "Decision Node"))
        nodes.add(Node("4", "Stop Node"))

        steps.add(Step("1", "Start", "Step 1", nodes[0]))
        steps.add(Step("2", "Is bulb on?", "Step 2", nodes[3]))
        steps.add(Step("3", "Stop", "Step 3", nodes[3]))
    }

    override fun getAll(flowId: String): Observable<Result<List<Step>>> {
        return Observable.just(Result.OnSuccess(steps))
    }
}
