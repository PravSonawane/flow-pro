package ui.feature.flow.stepdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.Step
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
        val binding: FragmentFlowStepDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_flow_step_details, container, false)

        // dagger injection
        stepDetailsComponent.injectIn(this)

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
}
