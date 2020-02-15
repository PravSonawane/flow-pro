package ui.lib.views

import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import ui.lib.base.BaseViewModel
import ui.lib.utils.StreamFactory

abstract class ItemViewModel<Input, Output>(
    analyticsKey: String,
    streamFactory: StreamFactory,
    @LayoutRes val layoutId: Int,
    @IntegerRes val variableId: Int,
    val viewType: Int
) : BaseViewModel<Input, Output>(analyticsKey, streamFactory)
