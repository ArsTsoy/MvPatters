package kz.example.mvpatterns.mvc

import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

class MainController(private val listener: MainModelListener) {

    private val model: MainModel = MainModel(listener)

    fun onLoginClicked(username: String, password: String) {
        if (username.isNotBlank() && password.isNotBlank()) {
            val disposable = Completable.fromAction {
                model.login(username, password)
            }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.i("myMainController", "onComplete")
                }, {
                    Log.i("myMainController", "onError: $it")
                })

        }
    }
}