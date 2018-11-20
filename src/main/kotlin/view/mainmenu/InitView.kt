package view.mainmenu

import javafx.geometry.Pos
import tornadofx.*
import view.login.LoginView
import view.psychologist.AllResultsView
import view.register.RegisterView
import view.statistics.StatisticsView

class InitView : View() {

    override val root = vbox {
        addClass(Styles.wrapper)
        label {
            text = "Здравствуйте!\nЗарегистрируйтесь или войдите в систему, чтобы получить доступ к тесту"
            addClass(Styles.header)
        }
        button("Войти") {
            setOnAction { replaceWith(LoginView::class) }
        }

        button("Зарегистрироваться") {
            setOnAction { replaceWith(RegisterView::class) }
        }
    }
}