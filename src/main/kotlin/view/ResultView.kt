package view

import javafx.geometry.Pos
import tornadofx.*
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
    }
}