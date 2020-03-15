package ui.feature.flow.stepdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.Flow
import domain.models.flow.Step
import domain.models.flow.StepType
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.stepdetails.databinding.FragmentFlowStepDetailsBinding
import ui.navigation.navigate
import javax.inject.Inject

class StepDetailsFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(StepDetailsViewModel::class.java)
    }

    private val stepDetailsComponent: StepDetailsComponent by lazy {
        DaggerStepDetailsComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // dagger injection
        stepDetailsComponent.injectIn(this)

        val binding: FragmentFlowStepDetailsBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId = arguments?.getString(resources.getString(R.string.deeplink_flow_step_details_path_param_flow_id))
            ?: throw IllegalStateException("Flow ID is required")
        val stepId = arguments?.getString(resources.getString(R.string.deeplink_flow_step_details_path_param_step_id))
            ?: throw IllegalStateException("Step ID is required")
        viewModel.sendInput(
            StepDetailsViewModel.Input.FlowId(
                flowId
            )
        )
        viewModel.sendInput(
            StepDetailsViewModel.Input.StepId(
                stepId
            )
        )

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is StepDetailsViewModel.Event.OnNewStep -> {}
                    is StepDetailsViewModel.Event.OnStepDetails -> handleOnStepDetails(flowId, it.step)
                    is StepDetailsViewModel.Event.OnAddInputStep -> handleOnAddInputStep(it.flow, it.step)
                    is StepDetailsViewModel.Event.OnAddOutputStep -> handleOnAddOutputStep(it.flow, it.step)
                }
            }

        return binding.root
    }

    private fun handleOnStepDetails(flowId: String, step: Step) {
        val pathParams = mapOf(
            R.string.deeplink_flow_step_details_path_param_flow_id to flowId,
            R.string.deeplink_flow_step_details_path_param_step_id to step.id
        )
        navigate(
            this,
            R.string.deeplink_flow_step_details,
            pathParams
        )
    }

    private fun handleOnAddInputStep(flow: Flow, step: Step) {
        val pathParams = mapOf(
            R.string.deeplink_flow_step_list_path_param_flow_id to flow.id
        )

        val queryParams = mapOf(
            R.string.deeplink_flow_step_list_query_param_step_id to step.id,
            R.string.deeplink_flow_step_list_query_param_step_type to StepType.INPUT.toString()
        )
        navigate(
            this,
            R.string.deeplink_flow_step_list,
            pathParams,
            queryParams
        )
    }

    private fun handleOnAddOutputStep(flow: Flow, step: Step) {
        val pathParams = mapOf(
            R.string.deeplink_flow_step_list_path_param_flow_id to flow.id
        )

        val queryParams = mapOf(
            R.string.deeplink_flow_step_list_query_param_step_id to step.id,
            R.string.deeplink_flow_step_list_query_param_step_type to StepType.OUTPUT.toString()
        )
        navigate(
            this,
            R.string.deeplink_flow_step_list,
            pathParams,
            queryParams
        )
    }
}
