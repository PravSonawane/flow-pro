package ui.feature.flow.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import core.lib.usecase.ObservableResultUseCase
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.flow.list.databinding.FragmentFlowListBinding
import ui.navigation.NavigationConfig
import ui.navigation.SimpleNavigationConfig
import ui.navigation.usecases.NavigationUseCase
import javax.inject.Inject
import javax.inject.Named

class FlowListFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    @Named(NavigationUseCase.NAMED)
    lateinit var navigationUseCase: ObservableResultUseCase<NavigationConfig, String>

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
        val config = SimpleNavigationConfig(R.string.deeplink_newflow_title)
        compositeDisposable += navigationUseCase.invoke(config)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun handleOnViewFlow(flow: Flow) {
        val pathParams = mapOf(
            R.string.deeplink_flow_step_details_path_param_flow_id to flow.id
        )
        val config = SimpleNavigationConfig(
            R.string.deeplink_flow_step_list,
            pathParams
        )
        compositeDisposable += navigationUseCase.invoke(config)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}
