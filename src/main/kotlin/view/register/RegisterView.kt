package view.register

import enity.User
import javafx.scene.control.*
import javafx.scene.text.TextAlignment
import tornadofx.*
import view.mainmenu.InitView
import view.mainmenu.UserView

class RegisterView : View() {
    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    var loginField: TextField by singleAssign()
    var passwordField: PasswordField by singleAssign()
    var birthday: DatePicker by singleAssign()
    var employment: TextField by singleAssign()
    private val sexGroup = ToggleGroup()

    var invalidMsg = label("") {
        textAlignment = TextAlignment.CENTER
    }

    private val controller: RegisterController by inject()

    override val root = form {
        fieldset("Personal Info") {
            field("First Name") {
                firstNameField = textfield()
            }
            field("Last Name") {
                lastNameField = textfield()
            }
            field("Birthday") {
                birthday = datepicker() {
                }
            }
            field("Sex"){
                hbox(10.0) {
                    radiobutton("Female", sexGroup).userData = 1
                    radiobutton("Male", sexGroup).userData  = 0
                }
            }
            field("Employment") {
                employment = textfield()
            }
        }

        fieldset("Auth") {
            field("Login")  {
                loginField = textfield()
            }
            field("Password")  {
                passwordField = passwordfield()
            }
        }

        add(invalidMsg)
        button("Зарегистрироваться") {
            addClass(Styles.navBtn)
            setOnAction {
                invalidMsg.removeFromParent()
                var result = false
                runAsyncWithProgress {
                    val user = User(firstName = firstNameField.text, lastName = lastNameField.text, login = loginField.text, password = passwordField.text,
                            birthday = birthday.value, employment = employment.text, sex = getSex())
                    result = controller.register(user)
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

        button("back") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(InitView::class)
            }
        }
    }

    private fun invalidAuth() {
        invalidMsg.text = "Register failed, try again"
    }

    private fun getSex() =
        when(sexGroup.selectedToggle.userData.toString()) {
            "0" -> 0
            "1" -> 1
            else -> 1
        }//ToDO: please change this govnocode

    fun clear() {
        firstNameField.text = ""
        lastNameField.text = ""
        loginField.text = ""
        passwordField.text = ""
        employment.text = ""
    }

}