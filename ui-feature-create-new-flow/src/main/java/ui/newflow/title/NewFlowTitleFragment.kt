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
import domain.models.flow.Flow
import ui.feature.create.newflow.databinding.FragmentNewFlowTitleBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.feature.create.newflow.R
import ui.newflow.createstep.CreateStepFragment
import ui.newflow.selectnode.NewFlowSelectNodeFragment
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
        binding.lifecycleOwner = this

        //dagger injection
        newFlowTitleComponent.injectIn(this)

        binding.viewModel = viewModel

        compositeDisposable += viewModel.observeOutput()
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
        bundle.putString(CreateStepFragment.ARG_FLOW_ID, flow.id)
        findNavController().navigate(R.id.fragment_create_step, bundle)
    }
}