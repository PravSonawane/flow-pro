package ui.feature.flow.steplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.StepType
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.steplist.databinding.FragmentStepListBinding
import ui.navigation.Navigator
import ui.navigation.SimpleNavigationConfig
import javax.inject.Inject

class StepListFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

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
        // dagger injection
        stepListComponent.injectIn(this)

        val binding: FragmentStepListBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId =
            arguments?.getString(resources.getString(R.string.deeplink_flow_step_list_path_param_flow_id))
        val stepId =
            arguments?.getString(resources.getString(R.string.deeplink_flow_step_list_query_param_step_id))
        val stepType = convertToStepType(
            arguments?.getString(resources.getString(R.string.deeplink_flow_step_list_query_param_step_type))
        )
        viewModel.sendInput(StepListScreenViewModel.Input(flowId, stepId, stepType))

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is StepListScreenViewModel.Event.OnViewStep -> handleOutput(it)
                    is StepListScreenViewModel.Event.OnCreateStep -> handleOutput(it)
                }
            }

        return binding.root
    }

    private fun convertToStepType(stepType: String?): StepType? {
        return when (stepType) {
            StepType.INPUT.toString() -> StepType.INPUT
            StepType.OUTPUT.toString() -> StepType.OUTPUT
            else -> null
        }
    }

    private fun handleOutput(event: StepListScreenViewModel.Event.OnViewStep) {
        val pathParams = mapOf(
            R.string.deeplink_flow_step_details_path_param_flow_id to event.flowId,
            R.string.deeplink_flow_step_details_path_param_step_id to event.step.id
        )
        val config = SimpleNavigationConfig(
            R.string.deeplink_flow_step_details,
            pathParams
        )
        navigator.navigate(config)
    }

    private fun handleOutput(event: StepListScreenViewModel.Event.OnCreateStep) {
        val pathParams = mapOf(
            R.string.deepLink_flow_create_step_path_param_flow_id to event.flowId
        )
        val config = SimpleNavigationConfig(
            R.string.deepLink_flow_create_step,
            pathParams
        )
        navigator.navigate(config)
    }
}
