import javafx.application.Application
import javafx.stage.Stage
import tornadofx.*

class LoginApp : App(MyView::class) {

}

fun main(args: Array<String>) {
    Application.launch(LoginApp::class.java, *args)
}