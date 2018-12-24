package view.help

import enity.Interpretation
import enity.Result
import javafx.geometry.Pos
import tornadofx.*
import view.getInterpretation
import view.mainmenu.UserView
import view.test.TestController
import view.test.TestView

class HelpView : View() {

    override val root = vbox(10.0) {
        alignment = Pos.CENTER
        spacing = 10.0
        label {
            isWrapText = true
            alignment = Pos.CENTER
            text = "В ходе тестирования необходимо поочередно в пяти таблицах, на которых в случайном порядке расположены числа от 1 до 25, отыскать и указать числа в порядке их возрастания. Ваша задача – пройти тест как можно быстрее и с наименьшим количеством ошибок."
        }

        button("Приступить") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(TestView::class)
            }
        }

        button("Назад") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }
}