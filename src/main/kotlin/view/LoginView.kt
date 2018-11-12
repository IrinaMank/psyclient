package view
import tornadofx.*
import com.google.gson.Gson
import javafx.scene.control.TextField
import enity.User
import enity.UserEntry
import enity.Users
import javafx.beans.binding.Bindings.select
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


class LoginView : View() {
    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    var id = 0

    override val root = vbox {
        hbox {
            label("First Name")
            firstNameField = textfield()
        }
        hbox {
            label("Last Name")
            lastNameField = textfield()
        }
        button("LOGIN") {
            useMaxWidth = true
            setOnAction { login() }
        }
    }

    fun login() {
        transaction {
            val result = UserEntry.find { Users.login eq firstNameField.text and (Users.password eq lastNameField.text) }
                if (!result.empty()) {
                    result.forEach {
                        UserData.id = it.id.value
                        replaceWith(UserView::class)
                    }

            }
        }
    }

}