package view.interpretation

import javafx.geometry.Pos
import tornadofx.*

class InterpretationView : View() {

    val controller: InterpretationController by inject()

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        label {
            val result = controller.getInterpretation()
            result?.let {
                text = if (it) "You are better then others, wow" else "You are worse then others, fuu"
            }
        }
    }
}