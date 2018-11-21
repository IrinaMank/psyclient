package view.login

import com.sun.javafx.application.PlatformImpl.runLater
import database.DbProvider
import tornadofx.Controller

class LoginController: Controller() {

    private val db = DbProvider.getDb()
    val loginScreen: LoginView by inject()

    fun login(login: String, password: String): Boolean {
        var isValid = false
        val user =  db.login(login, password)
        user?.let {
            UserData.user = it
            isValid = true
        }
        return isValid
    }

    fun showLoginScreen(message: String, shake: Boolean = false) {
        loginScreen.replaceWith(LoginView::class)
    }//ToDo: add shaking https://github.com/edvin/tornadofx-samples/blob/master/login/src/main/kotlin/no/tornado/fxsample/login/LoginController.kt

}