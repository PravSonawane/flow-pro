package ui.feature.newflow.createstep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.Flow
import ui.feature.create.newflow.databinding.FragmentCreateStepBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.create.newflow.R
import ui.feature.newflow.selectnode.NewFlowSelectNodeFragment
import javax.inject.Inject

class CreateStepFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        val binding: FragmentCreateStepBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_step, container, false)

        // dagger injection
        createStepComponent.injectIn(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId = arguments?.getString(ARG_FLOW_ID)
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
        val bundle = Bundle()
        bundle.putString(NewFlowSelectNodeFragment.ARG_FLOW_ID, flow.id)
        findNavController().navigate(R.id.fragment_new_flow_select_node, bundle)
    }

    private fun onError() {
        Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_FLOW_ID = "FLOW_ID"
    }
}
