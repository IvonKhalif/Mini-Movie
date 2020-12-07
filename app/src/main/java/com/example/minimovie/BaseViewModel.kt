package com.example.minimovie

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {
    private val disposable = CompositeDisposable()

    fun addToDisposable(disposable: Disposable) {
        this.disposable.remove(disposable)
        this.disposable.add(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}