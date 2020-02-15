package ui.newflow.selectnode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import ui.feature.create.newflow.databinding.FragmentNewFlowSelectNodeBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.create.newflow.R
import javax.inject.Inject

class NewFlowSelectNodeFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(NewFlowSelectNodeViewModel::class.java)
    }

    private val newFlowSelectNodeComponent: NewFlowSelectNodeComponent by lazy {
        DaggerNewFlowSelectNodeComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewFlowSelectNodeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_flow_select_node, container, false)

        //dagger injection
        newFlowSelectNodeComponent.injectIn(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId = arguments?.getString(ARG_FLOW_ID)
            ?: throw IllegalStateException("Flow ID is required")
        viewModel.sendInput(NewFlowSelectNodeViewModel.Input.FlowId(flowId))

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it) {
                    NewFlowSelectNodeViewModel.Event.OnNext -> {}
                }
            }

        return binding.root
    }

    companion object {
        const val ARG_FLOW_ID = "FLOW_ID"
    }
}