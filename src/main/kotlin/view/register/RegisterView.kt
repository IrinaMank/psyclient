package view.register

import enity.User
import javafx.scene.control.SelectionMode
import javafx.scene.control.TextField
import tornadofx.*
import view.UserView

class RegisterView : View() {
    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    var loginField: TextField by singleAssign()
    var passwordField: TextField by singleAssign()
    var age: TextField by singleAssign()
    var sex = listview(listOf("Male","Female").observable())
    var employment: TextField by singleAssign()

    var invalidMsg = hbox {
        hbox {
            //isVisible = false
            label("Login")
        }
    }

    private val controller: RegisterController by inject()

    override val root = vbox {
        hbox {
            label("First Name")
            firstNameField = textfield()
        }
        hbox {
            label("Last Name")
            lastNameField = textfield()
        }

        hbox {
            label("Login")
            loginField = textfield()
        }

        hbox {
            label("Password")
            passwordField = textfield()
        }

        hbox {
            label("Age")
            age = textfield()
        }

        hbox {
            label("Employment")
            employment = textfield()
        }

        hbox {
            this.add(sex)
            sex.selectionModel.selectionMode = SelectionMode.SINGLE
        }

        button("REGISTER") {
            useMaxWidth = true
            setOnAction {
                invalidMsg.removeFromParent()
                var result = false
                runAsyncWithProgress {
                    val user = User(firstName = firstNameField.text, lastName = lastNameField.text, login = loginField.text, password = passwordField.text,
                            age = age.text.toInt(), employment = employment.text, sex = 1)
                    result = controller.register(user)
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