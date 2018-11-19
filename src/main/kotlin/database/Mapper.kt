package database

import enity.*
import org.jetbrains.exposed.sql.Date
import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.DateTime

class Mapper {

    fun userFromEntryToDomain(user: UserEntry) =
            User(
                    user.id.value,
                    user.login,
                    user.password,
                    user.firstName,
                    user.lastName,
                    user.age,
                    user.sex,
                    user.employment,
                    user.userType
            )

    fun resultFromEntryToDomain(result: ResultEntry): Result {
        val results = arrayListOf<Float>()
        results.add(result.time1)
        results.add(result.time2)
        results.add(result.time3)
        results.add(result.time4)
        results.add(result.time5)
        return Result(results, result.date)
    }

}