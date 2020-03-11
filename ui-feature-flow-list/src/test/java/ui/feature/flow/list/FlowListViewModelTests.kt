package ui.feature.flow.list

import app.di.test.BaseViewModelTest
import app.di.test.TestAppViewModelFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class FlowListViewModelTests : BaseViewModelTest() {

    @Inject
    lateinit var viewModelFactory: TestAppViewModelFactory

    private lateinit var viewModel: FlowListViewModel

    private val flowTitleComponent: TestFlowListComponent =
        DaggerTestFlowListComponent.builder()
            .mainComponent(mainComponent())
            .build()

    init {
        flowTitleComponent.injectIn(this)
    }

    @Before
    fun before() {
        viewModel = viewModelFactory.create(FlowListViewModel::class.java)
    }

    @Test
    fun test() {
        assertEquals(true, viewModel != null)
    }
}
