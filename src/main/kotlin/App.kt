import javafx.application.Application
import tornadofx.*

class LoginApp : App(LoginView::class) {

}

fun main(args: Array<String>) {
    Application.launch(LoginApp::class.java, *args)
}