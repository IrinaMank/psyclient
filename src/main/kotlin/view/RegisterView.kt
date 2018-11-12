package view

import com.google.gson.Gson
import database.MnpDatabase
import enity.User
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.control.TextField
import tornadofx.*

class RegisterView : View() {
    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    var loginField: TextField by singleAssign()
    var passwordField: TextField by singleAssign()
    var age: TextField by singleAssign()
    var sex = listview(listOf("Male","Female").observable())
    var employment: TextField by singleAssign()

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
            setOnAction { login() }
        }
    }

    fun login() {
        //ToDO: if already exists
        val user = User(firstName = firstNameField.text, lastName = lastNameField.text, login = loginField.text, password = passwordField.text,
                age = age.text.toInt(), employment = employment.text, sex = 1)
        MnpDatabase().addUser(user)
        val bodyJson = Gson().toJson(user)
//        val (request, response, result) = Fuel.post("https://psykotlin.herokuapp.com/register")
//                .body(bodyJson)
//                .responseString()
//        result.fold({ print("dlf") }, {print("${it.response}")})
        replaceWith(UserView::class)
    }

}