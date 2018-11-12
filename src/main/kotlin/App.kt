import javafx.application.Application
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*

class LoginApp : App(LoginView::class) {
    override fun init() {
        super.init()
        Database.connect(
                "jdbc:mysql://db4free.net:3306/mnp_kotlin_db", driver = "com.mysql.jdbc.Driver",
                user = "imenidebora", password = "12345678")
        transaction {
            SchemaUtils.create(Users)
//        if (UserEntry.count() == 0) {
//            UserEntry.new {
//                text = "Thank you for stopping by!"
//                creation = DateTime.now()
//            }
//        }
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(LoginApp::class.java, *args)

}

object Users: IntIdTable() {
    val login = integer("login")
//    val password = varchar("password", 20)
//    val firstName = varchar("firstName", 20)
//    val lastName = varchar("lastName", 20)
//    val age = integer("age")
//    val sex = bool("sex")
//    val employment = varchar("employment", 30)
}


class UserEntry(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UserEntry>(Users)
    var login by Users.login
//    var password by Users.password
//    var firstName by Users.firstName
//    var lastName by Users.lastName
//    var age by Users.age
//    var sex by Users.sex
//    var employment by Users.employment
}