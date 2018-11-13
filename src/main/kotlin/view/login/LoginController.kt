package view.login

import database.DbProvider
import tornadofx.Controller

class LoginController: Controller() {

    private val db = DbProvider.getDb()

    fun login(login: String, password: String): Boolean {
        var isValid = false
        val user =  db.login(login, password)
        user?.let {
            UserData.id = user.id
            UserData.type = user.userType
            isValid = true
        }
        return isValid
    }

}