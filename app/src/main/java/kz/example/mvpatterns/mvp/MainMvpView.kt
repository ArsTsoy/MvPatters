package kz.example.mvpatterns.mvp

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

interface MainMvpView {

    fun showProgressBar()
    fun hideProgressBar()

    fun showToast(text: String)

    fun onError(e: Throwable)
}