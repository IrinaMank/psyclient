
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import tornadofx.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import javafx.scene.control.TextField
import sun.rmi.runtime.Log
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Handler
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response


class MyView : View() {
    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()

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
        val user = User(firstName = firstNameField.text, lastName = lastNameField.text)
        val bodyJson = Gson().toJson(user)
        val (request, response, result) = Fuel.post("https://psykotlin.herokuapp.com/register")
                .body(bodyJson)
                .header("Content-Type" to "application/json")
                .responseString()
        result.fold({ print("dlf") }, {print("${it.response}")})
    }

}