package database

import enity.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime


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

    fun uploadResult(result: Result) {
        transaction {
            ResultEntry.new {
                this.userId = UserData.id!!//ToDo: remove !!
                this.time1 = result.time[0]
                this.time2 = result.time[1]
                this.time3 = result.time[2]
                this.time4 = result.time[3]
                this.time5 = result.time[4]
                this.date = DateTime()
            }
        }
    }

//
//    fun getAverageResult(): Result {
//        var averageTime: Float = 0f
//        var averageMistakes = 0
//        transaction {
//            averageTime = Results.selectAll().map { it[Results.time1]+it[Results.time2]+it[Results.time3]+it[Results.time4]+it[Results.time5] }.average().toFloat()
//        }
//        return Result(averageTime, averageMistakes)
//    }
//
//    fun getUserAverage(id: Int): Result {
//        var averageTime: Float = 0f
//        var averageMistakes = 0
//        transaction {
//            val results = Results.select{ Results.userId eq id }
//            averageTime = results.map { it[Results.time1]+it[Results.time2]+it[Results.time3]+it[Results.time4]+it[Results.time5] }.average().toFloat()
//        }
//        return Result(averageTime, averageMistakes)
//    }

}