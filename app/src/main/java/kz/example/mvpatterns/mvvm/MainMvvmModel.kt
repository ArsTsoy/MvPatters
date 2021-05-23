package kz.example.mvpatterns.mvvm

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

class MainMvvmModel {

    fun login(username: String, password: String): Boolean {
        Thread.sleep(3_000L)
        return username == "admin" && password == "admin"
    }
}