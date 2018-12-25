package view.error

import javafx.geometry.Pos
import tornadofx.*
import view.mainmenu.UserView
import view.test.TestView

class ErrorView : View() {

    override val root = vbox(10.0) {
        alignment = Pos.CENTER
        spacing = 10.0
        label {
            isWrapText = true
            alignment = Pos.CENTER
            text = "Tестирование завершено, т.к. превышен временной лимит ожидания ответа"
        }

        button("В главное меню") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }
}