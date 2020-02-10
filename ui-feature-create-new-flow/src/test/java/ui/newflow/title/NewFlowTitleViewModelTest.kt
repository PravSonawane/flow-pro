package ui.newflow.title

import data.flow.repositories.DefaultFlowRepository
import domain.flow.usecases.SaveOrUpdateFlowUseCase
import junit.framework.Assert.assertEquals
import org.junit.Test

class NewFlowTitleViewModelTest {

    @Test
    fun `GIVEN no flow name WHEN going to next screen THEN flow name should be default`() {
        // given
        val defaultFlowName = "$DEFAULT_FLOW_NAME_PREFIX 1"
        val flowRepository = DefaultFlowRepository()
        val saveOrUpdateFlowUseCase = SaveOrUpdateFlowUseCase(flowRepository)

        // when
        val newFlowTitleViewModel = NewFlowTitleViewModel(saveOrUpdateFlowUseCase)

        // then
        assertEquals(defaultFlowName, newFlowTitleViewModel.title)
    }
}