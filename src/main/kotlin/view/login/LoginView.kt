package view.login

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

    override val root = vbox {
        hbox {
            label("Login")
            loginField = textfield()
        }
        hbox {
            label("Password")
            passwordField = textfield()
        }
        button("LOGIN") {
            useMaxWidth = true
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