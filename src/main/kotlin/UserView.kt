import test.TestView
import tornadofx.*

class UserView : View() {

    override val root = vbox {
        button {
            text = "Attempt test"
            setOnAction {
                replaceWith(TestView::class)
            }
        }
        button {
            text = "View my results"
        }
    }

}

