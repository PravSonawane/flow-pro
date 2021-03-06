package core.lib.rxutils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}
operator fun ReusableCompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
