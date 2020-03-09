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

    @Before
    fun before() {
        val flowTitleComponent: TestFlowListComponent =
            DaggerTestFlowListComponent.builder()
                .mainComponent(mainComponent())
                .build()

        flowTitleComponent.injectIn(this)
        viewModel = viewModelFactory.create(FlowListViewModel::class.java)
    }

    @Test
    fun test() {
        assertEquals(true, viewModel != null)
    }
}
