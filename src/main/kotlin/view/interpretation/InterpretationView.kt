package view.interpretation

import enity.Interpretation
import enity.Result
import javafx.geometry.Pos
import tornadofx.*
import view.mainmenu.UserView
import view.getInterpretation
import view.test.TestController

class InterpretationView : View() {

    val controller: TestController by inject()
    private val result: Result? = null
    private val workabilityTextView = label()
    private val persistenceTextView = label()

    override val root = vbox(10.0) {
//        prefWidth = 800.0
//        prefHeight = 600.0
//        alignment = Pos.CENTER
        val result = controller.resultObservable.getInterpretation()
        result.let {
            interpretationInText(it)
        }

        add(workabilityTextView)
        add(persistenceTextView)

        button("back") {
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