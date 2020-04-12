package data.feature.flow.test

import domain.flow.usecases.save.flow.CreateFlowInput
import domain.flow.usecases.save.node.CreateNodeInput
import domain.flow.usecases.save.step.CreateStepInput
import domain.models.flow.Flow
import domain.models.flow.Node
import domain.models.flow.Step

object FakeStorage {

    private var nodeId = 0
    private var stepId = 0
    private var flowId = 0

    private val nodeTable = HashMap<String, Node>()
    private val stepTable = HashMap<String, Step>()
    private val flowTable = HashMap<String, Flow>()

    private val startNode = Node(generateNodeId(), "Start Node", "Indicates start of flow")
    private val decisionNode = Node(generateNodeId(), "Decision Node", "Indicates a decision")
    private val processNode = Node(generateNodeId(), "Process Node", "Indicates a process")
    private val stopNode = Node(generateNodeId(), "Stop Node", "Indicates stop of flow")

    private var step1 = Step(generateStepId(), "Start", "Start step", startNode)
    private var step2 = Step(generateStepId(), "Turn on bulb", "Turn on bulb", processNode)
    private var step3 = Step(generateStepId(), "Is bulb on?", "Check if bulb is on", decisionNode)
    private var step4 = Step(generateStepId(), "Is bulb burnt?", "Check if bulb is burnt", decisionNode)
    private var step5 = Step(generateStepId(), "Stop", "Stop step", stopNode)

    init {
        step1 = step1.copy(inputSteps = emptyList(), outputSteps = listOf(step2))
        step2 = step2.copy(inputSteps = listOf(step1), outputSteps = listOf(step3))
        step3 = step3.copy(inputSteps = listOf(step2), outputSteps = listOf(step4, step5))
        step4 = step4.copy(inputSteps = listOf(step3), outputSteps = listOf(step2, step5))
        step5 = step5.copy(inputSteps = listOf(step3, step4), outputSteps = emptyList())
    }

    init {
        nodeTable[startNode.id] = startNode
        nodeTable[decisionNode.id] = decisionNode
        nodeTable[processNode.id] = processNode
        nodeTable[stopNode.id] = stopNode
    }

    init {
        stepTable[step1.id] = step1
        stepTable[step2.id] = step2
        stepTable[step3.id] = step3
        stepTable[step4.id] = step4
        stepTable[step5.id] = step5
    }

    init {
        val flow = Flow(
            generateFlowId(),
            name = "Flow name",
            state = Flow.State.DRAFT,
            steps = listOf(step1, step2, step3, step4, step5)
        )

        flowTable[flow.id] = flow
    }

    fun createNode(input: CreateNodeInput): Node {
        val node = Node(
            generateNodeId(),
            input.name,
            input.description
        )
        nodeTable[node.id] = node
        return node
    }

    fun createStep(input: CreateStepInput): Step {
        val step = Step(
            generateNodeId(),
            input.name,
            input.description,
            input.node,
            input.inputSteps,
            input.outputSteps
        )
        stepTable[step.id] = step
        return step
    }

    fun createFlow(input: CreateFlowInput): Flow {
        val flow = Flow(
            generateFlowId(),
            Flow.State.DRAFT,
            input.name,
            input.description,
            input.steps,
            input.paths
        )
        flowTable[flow.id] = flow
        return flow
    }

    fun getNodes(): List<Node> {
        return nodeTable.values.toList()
    }

    fun getFlow(id: String): Flow {
        return flowTable[id]!!
    }

    fun getAllFlows(): List<Flow> {
        return flowTable.values.toList()
    }

    fun getAllSteps(): List<Step> {
        return stepTable.values.toList()
    }

    private fun generateNodeId(): String {
        return "node${++nodeId}"
    }

    private fun generateStepId(): String {
        return "step${++stepId}"
    }

    private fun generateFlowId(): String {
        return "flow${++flowId}"
    }
}
