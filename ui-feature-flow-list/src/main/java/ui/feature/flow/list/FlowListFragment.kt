package ui.feature.flow.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.list.databinding.FragmentFlowListBinding
import ui.navigation.navigate
import javax.inject.Inject

class FlowListFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(FlowListViewModel::class.java)
    }

    private val flowListComponent: FlowListComponent by lazy {
        DaggerFlowListComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // dagger injection
        flowListComponent.injectIn(this)

        val binding: FragmentFlowListBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.includeToolbar.toolbar)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is FlowListViewModel.Event.OnViewFlow -> handleOnViewFlow(it.flow)
                    is FlowListViewModel.Event.OnCreateNewFlow -> handleOnCreateNewFlow()
                }
            }

        return binding.root
    }

    private fun handleOnCreateNewFlow() {
        navigate(this, R.string.deeplink_newflow_title)
    }

    private fun handleOnViewFlow(flow: Flow) {
        val pathParams = mapOf(
            R.string.deeplink_flow_step_details_path_param_flow_id to flow.id
        )
        navigate(
            this,
            R.string.deeplink_flow_step_list,
            pathParams
        )
    }
}
