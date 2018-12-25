package view.test

import enity.Result
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.ColumnConstraints
import org.joda.time.DateTime
import tornadofx.*
import view.error.ErrorView
import view.help.HelpView
import view.mainmenu.UserView
import kotlin.concurrent.timer

class TestView : View() {
    val MAX_TABLE_COUNT = 5
    val TABLE_SIZE = 5
    val numbers = (1..TABLE_SIZE*TABLE_SIZE).toList()
    val labelTime = text("Время, сек: ")
    var currentNumber = 1
    val currentNumLabel = text("Выберите число: $currentNumber")
    val maxTime = 3 * 60
    //    var seconds: Int  by Delegates.observable(0) {
//        prop, old, new ->
//        labelTime.text = new.toString()
//    }
    val loginController: TestController by inject()
    var timeBegin = System.currentTimeMillis()
    var tableNumber = 1
    var mistakes = 0
    val result = Result()
    val labelTable = text("Текущая таблица: $tableNumber")

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
        this.add(labelTable)
        this.add(currentNumLabel)
        text { text = "Время, сек" }
        this.add(labelTime)
        fillNumbers()
        add(testTable)

        button("Назад в Справку") {
            setOnAction {
                replaceWith(HelpView::class)
            }
        }

        button("Назад в Главное меню") {
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }

    init {
        timer(daemon = true, period = 1000) {
            mistakes ++
            if (mistakes > maxTime) {
                Platform.runLater {
                    maxTime()
                }
            }
            Platform.runLater {
                labelTime.text = mistakes.toString()
            }
        }
    }

    fun onClick(number: Int, button: Button) {
        if (currentNumber >= TABLE_SIZE * TABLE_SIZE) {
            if (tableNumber == MAX_TABLE_COUNT) {
                endTest()
            }
            val time = System.currentTimeMillis() - timeBegin
            result.time.add(time.toFloat())
            fillNumbers()
            currentNumber = 0
            tableNumber++
            mistakes = 0
            timeBegin = System.currentTimeMillis()
            labelTable.text = "Текущая таблица: $tableNumber"
        }
        currentNumber++
        currentNumLabel.text = "Выберите число: $currentNumber"
        button.textFill = javafx.scene.paint.Color.BLACK
    }

    fun fillNumbers() {
        testTable.getChildList()?.clear()
        val newNumbers = numbers.shuffled()
        for (r in 0 until TABLE_SIZE) {
            for (c in 0 until TABLE_SIZE) {
                val number = newNumbers[TABLE_SIZE * r + c]
                val button = Button(number.toString())
                button.setPrefSize(60.0, 60.0)
                button.setOnMousePressed {
                    if (number != currentNumber) {
                        button.addClass(Styles.mistakeBtn)
                    }
                }
                button.setOnMouseReleased {
                    if (number == currentNumber) {
                        onClick(number, button)
                    } else {
                        button.removeClass(Styles.mistakeBtn)
                    }
                }
                testTable.add(button, c, r)
            }
        }
        timeBegin = System.currentTimeMillis()
    }

    fun maxTime() {
        clearAll()
        replaceWith(ErrorView::class)
    }

    fun endTest() {
        clearAll()
        val time = System.currentTimeMillis() - timeBegin
        result.time.add(time.toFloat())
        result.date = DateTime()
        loginController.uploadResult(result)
        replaceWith(ResultView::class)
    }


    fun clearAll() {
        tableNumber = 1
        labelTime.text = "0"
        currentNumber = 1
        mistakes = 0
    }


    override fun onDock() {
        super.onDock()
        clearAll()
    }
}

