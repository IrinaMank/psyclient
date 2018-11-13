import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
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


        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://db4free.net:3306/mnp_kotlin_db"
        config.username = "imenidebora"
        config.password = "12345678"
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        val ds = HikariDataSource(config)

        Database.connect(ds)
    }
}

fun main(args: Array<String>) {
    Application.launch(LoginApp::class.java, *args)
}