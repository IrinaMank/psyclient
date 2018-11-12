package view

import view.test.TestView
import tornadofx.*

class UserView : View() {

    override val root = vbox {
        button {
            text = "Attempt view.test"
            setOnAction {
                replaceWith(TestView::class)
            }
        }
        button {
            text = "View my results"
        }
    }

}

