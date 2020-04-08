package ui.feature.flow.stepdetails

import app.di.test.BaseViewModelTest
import app.di.test.TestAppViewModelFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class StepDetailsViewModelTests : BaseViewModelTest() {

    @Inject
    lateinit var viewModelFactory: TestAppViewModelFactory

    private lateinit var viewModel: StepDetailsViewModel

    private val stepDetailsComponent: TestStepDetailsComponent =
        DaggerTestStepDetailsComponent.builder()
            .mainComponent(mainComponent())
            .build()

    init {
        stepDetailsComponent.injectIn(this)
    }

    @Before
    fun before() {
        viewModel = viewModelFactory.create(StepDetailsViewModel::class.java)
    }

    @Test
    fun test() {
        assertEquals(true, viewModel != null)
    }
}
