package view.login

import database.DbProvider
import tornadofx.Controller

class LoginController: Controller() {

    private val db = DbProvider.getDb()

    fun login(login: String, password: String): Boolean {
        var isValid = false
        val user =  db.login(login, password)
        user?.let {
            UserData.user = it
            isValid = true
        }
        return isValid
    }

}