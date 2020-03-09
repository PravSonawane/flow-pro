package ui.lib.views

import androidx.annotation.IntegerRes
import ui.lib.base.LayoutViewModel
import ui.lib.utils.StreamFactory

abstract class ItemViewModel<Input, Output>(
    analyticsKey: String,
    streamFactory: StreamFactory,
    layoutId: Int,
    @IntegerRes val variableId: Int
) : LayoutViewModel<Input, Output>(analyticsKey, streamFactory, layoutId)
