package view

import javafx.geometry.Pos
import view.test.TestView
import tornadofx.*
import view.interpretation.InterpretationView
import view.statistics.StatisticsView

class UserView : View() {

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        button {
            text = "Attempt view.test"
            setOnAction {
                replaceWith(TestView::class)
            }
        }
        button {
            text = "View my results"
            setOnAction {
                replaceWith(StatisticsView::class)
            }
        }

    }

}

