package ui.newflow.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.base.AppBaseFragment
import dev.curlybraces.ui_new_flow_title.R
import dev.curlybraces.ui_new_flow_title.databinding.FragmentNewFlowTitleBinding
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

        return binding.root
    }
}