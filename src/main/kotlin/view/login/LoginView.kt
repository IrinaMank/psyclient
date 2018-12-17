package view.login

import javafx.geometry.Pos
import javafx.scene.control.PasswordField
import tornadofx.*
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.text.TextAlignment
import view.mainmenu.InitView
import view.mainmenu.UserView


class LoginView : View() {
    private val loginController: LoginController by inject()

    var loginField: TextField by singleAssign()
    var passwordField: PasswordField by singleAssign()
    var invalidMsg = label("") {
        textAlignment = TextAlignment.CENTER
    }
    var loginHbox: HBox by singleAssign()
    //ToDo: add remember me

    override val root = vbox{
        addClass(Styles.wrapper)
        loginHbox = hbox {
            alignment = Pos.CENTER
            spacing = 10.0
            vbox {
                label("Логин")
                label("Пароль")
            }
            vbox {
                loginField = textfield()
                passwordField = passwordfield()
            }
        }
        add(invalidMsg)
        button("Войти") {
            addClass(Styles.navBtn)
            isWrapText = true
            setOnAction {
                invalidMsg.text = ""
                var result = false
                runAsyncWithProgress {
                    result = loginController.login(loginField.text, passwordField.text)
                }.setOnSucceeded {
                    if (result) {
                        clear()
                        replaceWith(UserView::class)
                    } else {
                        invalidAuth()
                    }
                }

            }
        }

        button("Назад") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(InitView::class)
            }
        }
    }

    private fun invalidAuth() {
        invalidMsg.text = "Что-то пошло не так, попробуйте еще раз"
    }

    fun clear() {
        loginField.text = ""
        passwordField.text = ""
    }

}