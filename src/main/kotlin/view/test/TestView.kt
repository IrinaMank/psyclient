package view.test

import enity.Result
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.ColumnConstraints
import tornadofx.*

class TestView : View() {
    val MAX_TABLE_COUNT = 5
    val TABLE_SIZE = 5
    val numbers = (1..25).toList()
    val labelTime = label("Time: ")
    var currentNumber = 1
    val currentNumLabel = text("Pick number: $currentNumber")
    //    var seconds: Int  by Delegates.observable(0) {
//        prop, old, new ->
//        labelTime.text = new.toString()
//    }
    val loginController: TestController by inject()
    var timeBegin = System.currentTimeMillis()
    var tableNumber = 1
    var mistakes = 0

    //val timer = timer(period = 1000, action = { seconds++ })

    val testTable = gridpane{
        alignment = Pos.CENTER
        setPrefSize(300.0,300.0)
        val column =  ColumnConstraints()
        column.prefWidth = 60.0
        //columnConstraints.addAll(column, column, column,column,column)
    }


    override val root = vbox {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
//        label {
//            text = "Pick number: $currentNumber"
//        }
        this.add(currentNumLabel)
        this.add(labelTime)
        fillNumbers()
        add(testTable)
    }

    fun onClick(number: Int, button: Button) {
        if (currentNumber == TABLE_SIZE * TABLE_SIZE) {
            if (tableNumber == MAX_TABLE_COUNT) {
                clearAll()
                val time = System.currentTimeMillis() - timeBegin
                loginController.uploadResult(Result(time.toFloat(), mistakes))
                loginController.replace()
            }
            fillNumbers()
            tableNumber++
        }
        currentNumber++
        currentNumLabel.text = "Pick number: $currentNumber"
        button.textFill = javafx.scene.paint.Color.BLACK
    }

    fun fillNumbers() {
        testTable.getChildList()?.clear()
        val newNumbers = numbers.shuffled()
        for (r in 0 until 5) {
            for (c in 0 until 5) {
                val number = newNumbers[5 * r + c]
                val button = Button(number.toString())
                button.setPrefSize(60.0, 60.0)
                button.setOnMousePressed {
                    if (number != currentNumber) {
                        button.textFill = javafx.scene.paint.Color.RED
                    }
                }
                button.setOnMouseReleased {
                    if (number == currentNumber) {
                        onClick(number, button)
                    } else {
                        mistakes++
                        button.textFill = javafx.scene.paint.Color.BLACK
                    }
                }
                testTable.add(button, c, r)
            }
        }
        timeBegin = System.currentTimeMillis()
    }


    fun clearAll() {
        tableNumber = 1
        labelTime.text = "0"
        currentNumber = 1
        mistakes = 0
    }

}

