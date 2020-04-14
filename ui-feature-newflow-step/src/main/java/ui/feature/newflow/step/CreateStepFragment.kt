package ui.feature.newflow.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.newflow.step.databinding.FragmentCreateStepBinding
import ui.navigation.Navigator
import ui.navigation.SimpleNavigationConfig
import javax.inject.Inject

class CreateStepFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CreateStepViewModel::class.java)
    }

    private val createStepComponent: CreateStepComponent by lazy {
        DaggerCreateStepComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // dagger injection
        createStepComponent.injectIn(this)

        val binding: FragmentCreateStepBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId =
            arguments?.getString(resources.getString(R.string.deepLink_flow_create_step_path_param_flow_id))
            ?: throw IllegalStateException("Flow ID is required")
        viewModel.sendInput(CreateStepViewModel.Input.FlowId(flowId))

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is CreateStepViewModel.Event.OnShowSelectNode -> onNext(it.flow)
                    is CreateStepViewModel.Event.OnNoFlowError -> onError()
                }
            }

        return binding.root
    }

    private fun onNext(flow: Flow) {
        val pathParams: Map<Int, String> = mapOf(
            R.string.deeplink_newflow_selectnode_path_param_flow_id to flow.id
        )
        val config = SimpleNavigationConfig(
            R.string.deeplink_newflow_selectnode,
            pathParams
        )
        navigator.navigate(config)
    }

    private fun onError() {
        Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
    }
}
