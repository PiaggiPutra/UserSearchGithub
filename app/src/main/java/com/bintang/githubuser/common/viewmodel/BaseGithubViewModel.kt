package com.bintang.githubuser.common.viewmodel

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import com.bintang.githubuser.base.viewmodel.BaseLifecycleViewModel
import com.bintang.githubuser.util.NoNetworkException
import com.bintang.githubuser.util.plusAssign

abstract class BaseGithubViewModel : BaseLifecycleViewModel() {

    lateinit var onShowProgress: () -> Unit

    lateinit var onHideProgress: () -> Unit

    lateinit var onShowNetworkError: () -> Unit

    lateinit var onShowOtherError: (message: String?) -> Unit

    protected val uiThreadSubject = BehaviorSubject.create<() -> Unit>()


    protected var isLoading = false

    /**
     * Pair<first, seconds>
     * first : background thread
     * seconds : ui thread
     */
    protected val databaseHelperSubject = BehaviorSubject.create<Pair<() -> Unit, () -> Unit>>()

    init {
        disposables += uiThreadSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it() }, {})

        disposables += databaseHelperSubject
                .observeOn(Schedulers.io())
                .map { (first, seconds) ->
                    first()
                    seconds
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it()
                }, {
                    it.printStackTrace()
                })
    }

    protected fun <T> progressCompose() =
            SingleTransformer<T, T> {
                it
                        .doOnSubscribe {
                            isLoading = true

                            uiThreadSubject.onNext {
                                if (::onShowProgress.isInitialized) {
                                    onShowProgress()
                                }
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess {
                            uiThreadSubject.onNext {
                                hideProgress()
                            }
                        }
            }

    protected fun onErrorThrowable(error: Throwable) {
        isLoading = false

        when (error) {
            is NoNetworkException -> {
                uiThreadSubject.onNext {
                    hideProgress()

                    if (::onShowNetworkError.isInitialized) {
                        onShowNetworkError()
                    }
                }
            }
            else -> {
                uiThreadSubject.onNext {
                    hideProgress()

                    if (::onShowOtherError.isInitialized) {
                        onShowOtherError(error.message)
                    }
                }
            }
        }

        error.printStackTrace()
    }

    private fun hideProgress() {
        if (::onHideProgress.isInitialized) {
            onHideProgress()
        }
    }
}