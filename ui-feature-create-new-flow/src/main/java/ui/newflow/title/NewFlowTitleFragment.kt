package ui.newflow.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.base.AppBaseFragment
import core.lib.rxutils.plusAssign
import domain.flow.models.Flow
import ui.feature.create_new_flow.databinding.FragmentNewFlowTitleBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.create_new_flow.R
import javax.inject.Inject

class NewFlowTitleFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        val binding: FragmentNewFlowTitleBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_flow_title, container, false)

        //dagger injection
        newFlowTitleComponent.injectIn(this)

        binding.viewModel = viewModel

        compositeDisposable += viewModel.events()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it) {
                    is NewFlowTitleViewModel.Event.OnNext -> onNext(it.flow)
                }
            }

        return binding.root
    }

    private fun onNext(flow: Flow) {
        val bundle = Bundle()
        bundle.putString("flowId", flow.id)
        findNavController().navigate(R.id.fragment_new_flow_select_node, bundle)
    }
}