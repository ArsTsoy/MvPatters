package kz.example.mvpatterns.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

class MainViewModel: ViewModel() {

    //region Vars
    private val model = MainMvvmModel()
    //endregion

    //region Live Data
    private val authResultLD: MutableLiveData<AuthResult> = MutableLiveData()
    fun observeAuthResult(): LiveData<AuthResult> {
        return authResultLD
    }

    private val loadingStateLD: MutableLiveData<LoadingState> = MutableLiveData()
    fun observeLoadingState(): LiveData<LoadingState> = loadingStateLD
    //endregion

    //region Actions
    fun onLoginClicked(username: String, password: String) {
        loadingStateLD.postValue(LoadingState.LOADING)
        if (username.isNotBlank() && password.isNotBlank()) {
            val disposable = Single.fromCallable {
                model.login(username, password)
            }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ isLoginSuccess ->
                    loadingStateLD.postValue(LoadingState.NOT_LOADING)

                    if(isLoginSuccess) {
                        authResultLD.postValue(AuthResult.Success("SUCCESS login"))
                    } else {
                        authResultLD.postValue(AuthResult.Error(Exception("Username or password is invalid")))
                    }
                }, {
                    loadingStateLD.postValue(LoadingState.NOT_LOADING)
                    authResultLD.postValue(AuthResult.Error(it))
                })

        }
    }
    //endregion


    // Момент когда View умирает(onDestroy())
    override fun onCleared() {
        Log.i("myMainViewModel", "onCleared")
        super.onCleared()
    }
}