package kz.example.mvpatterns.mvvm

/**
 * @author Arslan Tsoy <t.me/arslantsoy> on 5/23/21
 */

enum class LoadingState {
    LOADING, NOT_LOADING
}

sealed class AuthResult {
    class Success(val text: String): AuthResult()
    class Error(val error: Throwable): AuthResult()
}