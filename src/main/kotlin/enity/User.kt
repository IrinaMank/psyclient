package enity

import org.jetbrains.exposed.sql.Date
import org.joda.time.DateTime
import java.time.LocalDate

data class User(
        var id: Int = 0,
        val login: String = "",
        val password: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val birthday: java.time.LocalDate = LocalDate.now(),
        val sex: Int = 0,
        val employment: String = "",
        val userType: Int = 0
)