package ui.lib.base

import androidx.annotation.LayoutRes
import ui.lib.utils.StreamFactory

abstract class LayoutViewModel<Input, Output>(
    analyticsKey: String,
    streamFactory: StreamFactory,
    @LayoutRes val layoutId: Int
) : BaseViewModel<Input, Output>(analyticsKey, streamFactory)
