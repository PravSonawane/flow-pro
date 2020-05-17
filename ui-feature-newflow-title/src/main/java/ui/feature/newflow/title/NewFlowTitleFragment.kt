package ui.feature.newflow.title

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
import ui.feature.newflow.title.databinding.FragmentNewFlowTitleBinding
import ui.navigation.NavigationConfig
import ui.navigation.SimpleNavigationConfig
import ui.navigation.usecases.NavigationUseCase
import javax.inject.Inject
import javax.inject.Named

class NewFlowTitleFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    @Named(NavigationUseCase.NAMED)
    lateinit var navigationUseCase: ObservableResultUseCase<NavigationConfig, String>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(NewFlowTitleViewModel::class.java)
    }

    private val newFlowTitleComponent: NewFlowTitleComponent by lazy {
        DaggerNewFlowTitleComponent.builder()
            .mainComponent(mainComponent())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // dagger injection
        newFlowTitleComponent.injectIn(this)

        val binding: FragmentNewFlowTitleBinding =
            DataBindingUtil.inflate(inflater, viewModel.layoutId, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        compositeDisposable += viewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is NewFlowTitleViewModel.Event.OnNext -> handle(it.flow)
                }
            }

        return binding.root
    }

    private fun handle(flow: Flow) {
        val pathParams: Map<Int, String> = mapOf(
            R.string.deeplink_flow_step_list_path_param_flow_id to flow.id
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
