package view.test

import tornadofx.*
import view.getString
import view.interpretation.InterpretationView
import view.mainmenu.UserView

class ResultView : View() {
    val testController: TestController by inject()
    val result = find<TestController>().resultObservable

    override val root = vbox(10.0) {
        label {
            text = "You time: ${result.time[0].getString()}"
        }
        label {
            text = "You time: ${result.time[1].getString()}"
        }
        label {
            text = "You time: ${result.time[2].getString()}"
        }
        label {
            text = "You time: ${result.time[3].getString()}"
        }
        label {
            text = "You time: ${result.time[4].getString()}"
        }
        button {
            addClass(Styles.navBtn)
            text = "Watch interpretation"
            setOnAction {
                replaceWith(InterpretationView::class)
            }
        }

        button("back to main menu") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }
}