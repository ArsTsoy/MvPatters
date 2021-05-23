package kz.example.mvpatterns.mvp

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

class MainMvpModel {

    fun login(username: String, password: String): Boolean {
        Thread.sleep(3_000L)
        return username == "admin" && password == "admin"
    }
}