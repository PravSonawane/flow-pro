package ui.feature.flow.steplist

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
import ui.feature.flow.steplist.databinding.FragmentStepListBinding
import ui.navigation.navigate
import javax.inject.Inject

class StepListFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(StepListScreenViewModel::class.java)
    }

    private val stepListComponent: StepListComponent by lazy {
        DaggerStepListComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStepListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_step_list, container, false)

        // dagger injection
        stepListComponent.injectIn(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId =
            arguments?.getString(resources.getString(R.string.deeplink_flow_steps_path_param_flow_id))
                ?: throw IllegalStateException("Flow ID is required")
        val stepId = "step1"
            // TODO extract stepId
            // arguments?.getString(resources.getString(R.string.deeplink_flow_steps_path_param_step_id))
            //     ?: throw IllegalStateException("Step ID is required")
        viewModel.sendInput(StepListScreenViewModel.Input.InputStepList(flowId, stepId))

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is StepListScreenViewModel.Event.OnNewStep -> {
                    }
                    is StepListScreenViewModel.Event.OnViewStep -> handleOnViewStep(
                        it.flowId,
                        it.step
                    )
                }
            }

        return binding.root
    }

    private fun handleOnViewStep(flowId: String, step: Step) {
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
