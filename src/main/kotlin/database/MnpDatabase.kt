package database

import enity.User
import enity.UserEntry
import enity.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


class MnpDatabase {

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
}