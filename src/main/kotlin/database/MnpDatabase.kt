package database

import enity.User
import enity.UserEntry
import enity.Users
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction


class MnpDatabase {

    private val mapper = Mapper()

    fun addUser(user: User) {
        transaction {
            UserEntry.new{
                this.login = user.login
                this.password = user.password
                this.firstName = user.firstName
                this.lastName = user.lastName
                this.age = user.age
                this.sex = user.sex
                this.employment = user.employment
            }
        }
    }

    fun isLoginUnique(login: String): Boolean {
        var isUnique = false
        transaction {
            val result = UserEntry.find{ Users.login eq login}
            if (result.empty()) {
                isUnique = true
            }
        }
        return isUnique
    }

    fun login(login: String, password: String): User? {
        var user: User? = null
        transaction {
            val result = UserEntry.find { (Users.login eq login) and (Users.password eq password) }
            if (!result.empty()) {
                result.forEach {
                    user = mapper.userFromEntryToDomain(it)
                }
            }
        }
        return user
    }
}