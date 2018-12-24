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
            val id = db.addUser(user)
            success = true
            user.id = id
            UserData.user = user
        }
        return success
    }

}