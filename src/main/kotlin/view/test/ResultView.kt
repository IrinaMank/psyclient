package view.test

import enity.Interpretation
import javafx.geometry.Pos
import tornadofx.*
import view.getInterpretation
import view.getString
import view.mainmenu.UserView

class ResultView : View() {
    val testController: TestController by inject()
    val result = find<TestController>().resultObservable
    private val workabilityTextView = text()
    private val persistenceTextView = text()

    override val root = vbox(10.0) {
        alignment = Pos.CENTER
        setPrefSize(300.0,300.0)
        text {
            text = "Таблица 1: ${result.time[0].getString()}"
        }
        text {
            text = "Таблица 2: ${result.time[1].getString()}"
        }
        text {
            text = "Таблица 3: ${result.time[2].getString()}"
        }
        text {
            text = "Таблица 4: ${result.time[3].getString()}"
        }
        text {
            text = "Таблица 5: ${result.time[4].getString()}"
        }
        val result = result.getInterpretation()
        result.let {
            interpretationInText(it)
        }

        add(workabilityTextView)
        add(persistenceTextView)
//        button {
//            addClass(Styles.navBtn)
//            text = "Получить интерпретацию"
//            setOnAction {
//                replaceWith(InterpretationView::class)
//            }
//        }

        button("Назад") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }

    fun interpretationInText(interpretation: Interpretation) {
        if (interpretation.workability > 1) {
            workabilityTextView.text = "Хорошая степень врабатываемости"
        } else {
            workabilityTextView.text = "Вам требуется более длительная подготовка к основной работе, чем в среднем"
        }

        if (interpretation.persistence < 1) {
            persistenceTextView.text = "Хорошая психическая устойчивость"
        } else {
            persistenceTextView.text = "Уровень психической устойчивости ниже среднего"
        }
    }

}