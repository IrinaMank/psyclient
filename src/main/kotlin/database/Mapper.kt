package database

import enity.*
import java.time.LocalDate

class Mapper {

    fun userFromEntryToDomain(user: UserEntry) =
            User(
                    user.id.value,
                    user.login,
                    user.password,
                    user.firstName,
                    user.lastName,
                    LocalDate.parse(user.birthday) ,
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