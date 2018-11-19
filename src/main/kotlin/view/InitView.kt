package view

import javafx.geometry.Pos
import tornadofx.*
import view.login.LoginView
import view.psychologist.AllResultsView
import view.register.RegisterView
import view.statistics.StatisticsView

class InitView : View() {

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        button("LOGIN") {
            isWrapText = true
            setOnAction { replaceWith(LoginView::class) }
        }

        button("REGISTER") {
            isWrapText = true
            setOnAction { replaceWith(RegisterView::class) }
        }
        button("Result") {
            isWrapText = true
            setOnAction { replaceWith(AllResultsView::class) }
        }
    }
}