package database

import enity.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime


class MnpDatabase {

    private val mapper = Mapper()

    fun addUser(user: User): Int {
        var i = 0
        transaction {
            i = UserEntry.new{
                this.login = user.login
                this.password = user.password
                this.firstName = user.firstName
                this.lastName = user.lastName
                this.birthday = user.birthday.toString()
                this.sex = user.sex
                this.employment = user.employment
            }.id.value
        }
        return i
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
                this.user = UserEntry.find { Users.id eq UserData.user?.id }.firstOrNull() ?: UserEntry(id)
                this.time1 = result.time[0]
                this.time2 = result.time[1]
                this.time3 = result.time[2]
                this.time4 = result.time[3]
                this.time5 = result.time[4]
                this.date = DateTime()
            }
        }
    }

    fun getPersonalStatisticsByName(firstName: String, lastName: String): List<Result> {
        var userResults: List<Result> = arrayListOf<Result>()
        transaction {
            val user = UserEntry.find { (Users.firstName eq firstName) and (Users.lastName eq lastName) }.toList()
            if (user.isEmpty()) return@transaction
            userResults = user[0].results.map {
                    mapper.resultFromEntryToDomain(it)
            }
//            userResults = (Users innerJoin Results).slice(Results.columns).select {
//                Users.firstName.eq(firstName) and Users.lastName.eq(lastName)
//            }.map {
//                mapper.resultFromEntryToDomain(it)
//            }
        }
        return userResults
    }

    fun getPersonalStatisticsById(id: Int) : List<Result> {
        var userResults: List<Result> = arrayListOf<Result>()
        transaction {
            val user = UserEntry.find { Users.id eq id }.toList()
            if (user.isEmpty()) return@transaction
            userResults = user[0].results.map {
                mapper.resultFromEntryToDomain(it)
            }
        }
        return userResults
    }

    fun getAllStatistics(): List<PersonalResult>  {
        val allResults: MutableList<PersonalResult> = mutableListOf()
        transaction {
            UserEntry.all().forEach {
                val name = it.firstName+" "+it.lastName
                it.results.firstOrNull()?.let {
                    val result  = mapper.resultFromEntryToDomain(it)
                    allResults.add(PersonalResult(name, result))
                }
            }
        }
        return allResults
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
//            val results = Results.select{ Results.user eq id }
//            averageTime = results.map { it[Results.time1]+it[Results.time2]+it[Results.time3]+it[Results.time4]+it[Results.time5] }.average().toFloat()
//        }
//        return Result(averageTime, averageMistakes)
//    }

}