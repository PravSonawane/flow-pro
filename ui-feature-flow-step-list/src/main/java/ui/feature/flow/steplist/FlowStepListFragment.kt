package ui.feature.flow.steplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.steplist.databinding.FragmentFlowStepListBinding
import javax.inject.Inject

class FlowStepListFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(FlowStepListViewModel::class.java)
    }

    private val flowStepListComponent: FlowStepListComponent by lazy {
        DaggerFlowStepListComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFlowStepListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_flow_step_list, container, false)

        // dagger injection
        flowStepListComponent.injectIn(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId = arguments?.getString(ARG_FLOW_ID)
            ?: throw IllegalStateException("Flow ID is required")
        viewModel.sendInput(FlowStepListViewModel.Input.FlowId(flowId))

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    FlowStepListViewModel.Event.OnNewStep -> {}
                }
            }

        return binding.root
    }

    companion object {
        const val ARG_FLOW_ID = "FLOW_ID"
    }
}
