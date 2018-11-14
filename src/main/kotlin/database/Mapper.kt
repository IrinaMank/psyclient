package database

import enity.Result
import enity.ResultEntry
import enity.User
import enity.UserEntry
import org.jetbrains.exposed.sql.Date
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

}