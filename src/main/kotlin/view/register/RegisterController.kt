package view.register

import database.DbProvider
import enity.User
import tornadofx.Controller

class RegisterController: Controller() {

    private val db = DbProvider.getDb()

    fun checkLogin(login: String) =
            db.isLoginUnique(login)

    fun register(user: User): Boolean {
        var success = false
        val isLoginUnique = checkLogin(user.login)
        if (isLoginUnique) {
            db.addUser(user)
            success = true
        }
        return success
    }

}