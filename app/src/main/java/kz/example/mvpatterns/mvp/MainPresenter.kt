package kz.example.mvpatterns.mvp

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

class MainPresenter(private val mvpView: MainMvpView) {

    private val model: MainMvpModel = MainMvpModel()

    fun onLoginClicked(username: String, password: String) {
        mvpView.showProgressBar()
        if (username.isNotBlank() && password.isNotBlank()) {
            val disposable = Single.fromCallable {
                model.login(username, password)
            }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ isLoginSuccess ->
                    mvpView.hideProgressBar()
                    mvpView.showToast("Result: $isLoginSuccess")
                }, {
                    mvpView.hideProgressBar()
                    mvpView.onError(it)
                })

        }
    }
}