package view

import javafx.geometry.Pos
import tornadofx.*
import view.interpretation.InterpretationView
import view.mainmenu.UserView
import view.test.TestController

class ResultView : View() {
    val testController: TestController by inject()
    val result = find<TestController>().resultObservable

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        label {
            text = "You time: ${result.time}"
        }
        button {
            text = "Watch interpretation"
            setOnAction {
                replaceWith(InterpretationView::class)
            }
        }

        button("back to main menu") {
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }
}