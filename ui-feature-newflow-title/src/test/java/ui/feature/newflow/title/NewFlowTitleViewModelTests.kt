package ui.feature.newflow.title

import app.di.TestAppModule
import org.junit.Before
import org.junit.Test
import ui.lib.base.BaseTest

class NewFlowTitleViewModelTests : BaseTest() {

    //@Inject
    //lateinit var viewModelFactory: ViewModelProvider.Factory

    // @Inject
    // lateinit var viewModel: NewFlowTitleViewModel
    //
    // val appComponent = DaggerTestAppComponent.builder().build()
    //
    // val mainComponent = DaggerTestMainComponent.builder().appComponent(appComponent).build()
    //
    // private val newFlowTitleComponent: TestNewFlowTitleComponent by lazy {
    //     DaggerTestNewFlowTitleComponent.builder()
    //         .mainComponent(mainComponent)
    //         .build()
    // }

    @Before
    fun before() {
        //newFlowTitleComponent.injectIn(this)
    }

    @Test
    fun test() {
        val m = TestAppModule()
    }
}