package data.feature.flow.test

import domain.models.flow.Flow
import domain.models.flow.Node
import domain.models.flow.Step

object FakeStorage {

    private val startNode = Node("node1", "Start Node", "Indicates start of flow")
    private val decisionNode = Node("node2", "Decision Node", "Indicates a decision")
    private val processNode = Node("node3", "Process Node", "Indicates a process")
    private val stopNode = Node("node4", "Stop Node", "Indicates stop of flow")

    private var step1 = Step("step1", "Start", "Start step", startNode)
    private var step2 = Step("step2", "Turn on bulb", "Turn on bulb", processNode)
    private var step3 = Step("step3", "Is bulb on?", "Check if bulb is on", decisionNode)
    private var step4 = Step("step4", "Is bulb burnt?", "Check if bulb is burnt", decisionNode)
    private var step5 = Step("step5", "Stop", "Stop step", stopNode)

    fun getNodes(): List<Node> {
        return listOf(startNode, decisionNode, processNode, startNode)
    }

    fun getFlow(): Flow {

        step1 = step1.copy(inputSteps = emptyList(), outputSteps = listOf(step2))
        step2 = step2.copy(inputSteps = listOf(step1), outputSteps = listOf(step3))
        step3 = step3.copy(inputSteps = listOf(step2), outputSteps = listOf(step4, step5))
        step4 = step4.copy(inputSteps = listOf(step3), outputSteps = listOf(step2, step5))
        step5 = step5.copy(inputSteps = listOf(step3, step4), outputSteps = emptyList())

        return Flow(
            "1",
            state = Flow.State.DRAFT,
            steps = listOf(step1, step2, step3, step4, step5)
        )
    }
}
