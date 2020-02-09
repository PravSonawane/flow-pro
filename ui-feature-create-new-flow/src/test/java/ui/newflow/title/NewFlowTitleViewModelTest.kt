package ui.newflow.title

import junit.framework.Assert.assertEquals
import org.junit.Test

class NewFlowTitleViewModelTest {

    @Test
    fun `GIVEN no flow name WHEN going to next screen THEN flow name should be default`() {
        // given
        val defaultFlowName = "$DEFAULT_FLOW_NAME_PREFIX 1"

        // when
        val newFlowTitleViewModel = NewFlowTitleViewModel()

        // then
        assertEquals(defaultFlowName, newFlowTitleViewModel.title)
    }
}