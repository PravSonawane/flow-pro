package ui.lib.views

import androidx.lifecycle.MutableLiveData
import ui.lib.base.BaseViewModel
import javax.inject.Inject

class ListViewModel @Inject constructor(): BaseViewModel<Any>() {
    val data: MutableLiveData<Any> = MutableLiveData()
    var adapterClass: Any = Any::class.java
}