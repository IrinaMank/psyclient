import javafx.application.Application
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import tornadofx.*
import view.InitView

class LoginApp : App(InitView::class) {
    override fun init() {
        super.init()
        Database.connect(
                "jdbc:mysql://db4free.net:3306/mnp_kotlin_db", driver = "com.mysql.jdbc.Driver",
                user = "imenidebora", password = "12345678")
    }
}

fun main(args: Array<String>) {
    Application.launch(LoginApp::class.java, *args)
}