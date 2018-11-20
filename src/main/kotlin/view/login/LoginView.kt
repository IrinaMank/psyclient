package view.login

import javafx.geometry.Pos
import tornadofx.*
import javafx.scene.control.TextField
import view.mainmenu.InitView
import view.mainmenu.UserView


class LoginView : View() {
    private val loginController: LoginController by inject()

    var loginField: TextField by singleAssign()
    var passwordField: TextField by singleAssign()
    var invalidMsg = hbox {
        hbox {
            //isVisible = false
            label("Wrong login or password, try again")
        }
    }

    override val root = vbox{
        addClass(Styles.wrapper)
        hbox {
            alignment = Pos.CENTER
            spacing = 10.0
            vbox {
                label("Login")
                label("Password")
            }
            vbox {
                loginField = textfield("imenidebora")
                passwordField = textfield("123456")
            }
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

        button("back") {
            setOnAction {
                replaceWith(InitView::class)
            }
        }
    }

    private fun invalidAuth() {
        root.add(invalidMsg)
    }

}