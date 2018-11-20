import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javafx.application.Application
import javafx.stage.Stage
import org.jetbrains.exposed.sql.Database
import tornadofx.*
import view.mainmenu.InitView

class LoginApp : App(InitView::class, Styles::class) {

    override fun start(stage: Stage) {
        stage.isResizable = false

        super.start(stage)
    }

    override fun init() {
        super.init()
        reloadStylesheetsOnFocus()
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