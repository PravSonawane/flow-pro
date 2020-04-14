package ui.feature.flow.selectstep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.selectstep.databinding.FragmentFlowSelectStepBinding
import javax.inject.Inject

class SelectStepFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SelectStepViewModel::class.java)
    }

    private val selectStepComponent: SelectStepComponent by lazy {
        DaggerSelectStepComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // dagger injection
        selectStepComponent.injectIn(this)

        val binding: FragmentFlowSelectStepBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId = arguments?.getString(resources.getString(R.string.deeplink_newflow_selectstep_path_param_flow_id))
            ?: throw IllegalStateException("Flow ID is required")
        viewModel.sendInput(
            SelectStepViewModel.Input.FlowId(
                flowId
            )
        )

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    SelectStepViewModel.Event.OnNext -> {}
                }
            }

        return binding.root
    }
}
