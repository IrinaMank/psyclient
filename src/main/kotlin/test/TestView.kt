package test

import javafx.scene.control.Button
import sun.plugin2.message.Message
import tornadofx.*
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.fixedRateTimer


class TestView : View() {
    val numbers = (1..25).toList().shuffled()
    val labelTime = label("0")
    val textview = text("1")
    var currentNumber = 1
    val loginController: TestController by inject()
    val timeBegin = System.currentTimeMillis()



    override val root = vbox {

        label {
            text = "Pick number: "
        }
        this.add(textview)
        this.add(labelTime)
        gridpane {

            for (r in 0 until 5) {
                for (c in 0 until 5) {
                    val number = numbers[5 * r + c]
                    val button = Button(number.toString())
                    button.setOnMousePressed {
                        button.textFill = javafx.scene.paint.Color.RED
                    }
                    button.setOnMouseReleased { onClick(number, button) }
                    this.add(button, c, r)
                }
            }
        }
    }

    fun onClick(number: Int, button: Button) {
        if(number == 25) {
            val time = System.currentTimeMillis() - timeBegin
            labelTime.text = (time / 1000).toString()
            loginController.replace()
        }
        if (number== currentNumber) {
            textview.text = (number + 1).toString()
            currentNumber++
        }
        button.textFill = javafx.scene.paint.Color.BLACK
    }

}

