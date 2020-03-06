package ui.feature.flow.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.di.test.BaseTest
import app.di.test.TestAppViewModelFactory
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import javax.inject.Inject

class FlowListViewModelTests : BaseTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val schedulerRule = RxSchedulerRule()

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
