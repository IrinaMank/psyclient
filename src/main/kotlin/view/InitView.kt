package view

import tornadofx.*
import view.login.LoginView
import view.register.RegisterView

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