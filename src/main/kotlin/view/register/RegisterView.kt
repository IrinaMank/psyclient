package view.register

import enity.User
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Pos
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
    val selectedEmployment = SimpleStringProperty()
    private val sexGroup = ToggleGroup()
    val employments = FXCollections.observableArrayList("Школьник",
            "Студент","Работник")

    var invalidMsg = label("") {
        textAlignment = TextAlignment.CENTER
    }

    private val controller: RegisterController by inject()

    override val root = form {
        fieldset("Персональная информация") {
            field("Имя") {
                firstNameField = textfield()
            }
            field("Фамилия") {
                lastNameField = textfield()
            }
            field("Дата рождения") {
                birthday = datepicker()
            }
            field("Пол"){
                hbox(10.0) {
                    radiobutton("Female", sexGroup).userData = 1
                    radiobutton("Male", sexGroup).userData  = 0
                }
            }
            field("Занятость") {
                combobox(selectedEmployment, employments)
            }
        }

        fieldset("Аутентификация") {
            field("Логин")  {
                loginField = textfield()
            }
            field("Пароль")  {
                passwordField = passwordfield()
            }
        }

        add(invalidMsg)
        vbox {
            alignment = Pos.CENTER
            button("Зарегистрироваться") {
                addClass(Styles.navBtn)
                setOnAction {
                    invalidMsg.removeFromParent()
                    var result = false
                    runAsyncWithProgress {
                        val user = User(firstName = firstNameField.text, lastName = lastNameField.text, login = loginField.text, password = passwordField.text,
                                birthday = birthday.value, employment = selectedEmployment.value, sex = getSex())
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

            button("Назад") {
                addClass(Styles.navBtn)
                setOnAction {
                    replaceWith(InitView::class)
                }
            }
        }
    }

    private fun invalidAuth() {
        invalidMsg.text = "ЧТо-то пошло не так, попробуйте еще раз"
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
    }

}