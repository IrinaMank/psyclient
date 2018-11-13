package view.login

import javafx.geometry.Pos
import tornadofx.*
import javafx.scene.control.TextField
import view.UserView


class LoginView : View() {
    val loginController: LoginController by inject()

    var loginField: TextField by singleAssign()
    var passwordField: TextField by singleAssign()
    var invalidMsg = hbox {
        hbox {
            //isVisible = false
            label("Wrong login or password, try again")
        }
    }

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        hbox {
            alignment = Pos.CENTER
            label("Login")
            loginField = textfield("imenidebora")
        }
        hbox {
            alignment = Pos.CENTER
            label("Password")
            passwordField = textfield("123456")
        }
        button("LOGIN") {
            isWrapText = true
            setOnAction {
                invalidMsg.removeFromParent()
                var result = false
                runAsyncWithProgress {
                    result = loginController.login(loginField.text, passwordField.text)
                }.setOnSucceeded {
                    if (result) {
                        replaceWith(UserView::class)
                    } else {
                        invalidAuth()
                    }
                }

            }
        }
    }

    private fun invalidAuth() {
        root.add(invalidMsg)
    }

}