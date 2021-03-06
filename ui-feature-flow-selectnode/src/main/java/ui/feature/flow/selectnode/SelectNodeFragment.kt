package ui.feature.flow.selectnode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.selectnode.databinding.FragmentFlowSelectNodeBinding
import javax.inject.Inject

class SelectNodeFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SelectNodeViewModel::class.java)
    }

    private val selectNodeComponent: SelectNodeComponent by lazy {
        DaggerSelectNodeComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // dagger injection
        selectNodeComponent.injectIn(this)

        val binding: FragmentFlowSelectNodeBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val flowId = arguments?.getString(resources.getString(R.string.deeplink_newflow_selectnode_path_param_flow_id))
            ?: throw IllegalStateException("Flow ID is required")
        viewModel.sendInput(
            SelectNodeViewModel.Input.FlowId(
                flowId
            )
        )

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    SelectNodeViewModel.Event.OnNext -> {}
                }
            }

        return binding.root
    }
}
