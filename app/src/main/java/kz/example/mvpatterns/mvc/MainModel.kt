package kz.example.mvpatterns.mvc

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

class MainModel(private val listener: MainModelListener) {

    fun login(username: String, password: String) {
        Thread.sleep(3_000L)
        listener.onLoginResult(username == "admin" && password == "admin")
    }
}