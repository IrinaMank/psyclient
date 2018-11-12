package view

import com.google.gson.Gson
import enity.User
import javafx.scene.control.TextField
import tornadofx.*

class InitView : View() {

    override val root = vbox {
        button("LOGIN") {
            useMaxWidth = true
            setOnAction { replaceWith(LoginView::class) }
        }

        button("REGISTER") {
            useMaxWidth = true
            setOnAction { replaceWith(RegisterView::class) }
        }

    }
}