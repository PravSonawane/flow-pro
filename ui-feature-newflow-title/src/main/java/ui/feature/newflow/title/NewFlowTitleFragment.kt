package ui.feature.newflow.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.newflow.title.databinding.FragmentNewFlowTitleBinding
import ui.navigation.Navigator
import javax.inject.Inject

class NewFlowTitleFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

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
                    is NewFlowTitleViewModel.Event.OnNext -> onNext(it.flow)
                }
            }

        return binding.root
    }

    private fun onNext(flow: Flow) {
        val pathParams: Map<Int, String> = mapOf(
            R.string.deeplink_flow_step_list_path_param_flow_id to flow.id
        )
        navigator.navigate(R.string.deeplink_flow_step_list, pathParams)
    }
}
