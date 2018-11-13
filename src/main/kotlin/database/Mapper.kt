package database

import enity.User
import enity.UserEntry

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