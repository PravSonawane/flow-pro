package ui.feature.newflow.title

import app.di.test.BaseViewModelTest
import app.di.test.TestAppViewModelFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class NewFlowTitleViewModelTests : BaseViewModelTest() {

    @Inject
    lateinit var viewModelFactory: TestAppViewModelFactory

    private lateinit var viewModel: NewFlowTitleViewModel

    private val newFlowTitleComponent: TestNewFlowTitleComponent =
        DaggerTestNewFlowTitleComponent.builder()
            .mainComponent(mainComponent())
            .build()

    init {
        newFlowTitleComponent.injectIn(this)
    }

    @Before
    fun before() {
        viewModel = viewModelFactory.create(NewFlowTitleViewModel::class.java)
    }

    @Test
    fun test() {
        assertEquals(true, viewModel != null)
    }
}
