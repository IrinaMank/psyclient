package enity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Users: IntIdTable() {
    val login = varchar("login", 20)
    val password = varchar("password", 20)
    val firstName = varchar("firstName", 20)
    val lastName = varchar("lastName", 20)
    val birthday = varchar("birthday", 10)
    val sex = integer("sex")
    val employment = varchar("employment", 30)
    val userType = integer("userType")

}

class UserEntry(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UserEntry>(Users)
    var login by Users.login
    var password by Users.password
    var firstName by Users.firstName
    var lastName by Users.lastName
    var birthday by Users.birthday
    var sex by Users.sex
    var employment by Users.employment
    var userType by Users.userType
    val results by ResultEntry referrersOn Results.user
}