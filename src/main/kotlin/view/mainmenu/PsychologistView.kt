package view.mainmenu

import javafx.geometry.Pos
import view.test.TestView
import tornadofx.*
import view.interpretation.InterpretationView
import view.psychologist.AllResultsView
import view.statistics.PersonalStatistics
import view.statistics.StatisticsController
import view.statistics.StatisticsView

class PsychologistView : View() {

    private val statisticsController: StatisticsController by inject()
    private val psychologistRoot = vbox(10.0) {
        addClass(Styles.wrapper)
        button {
            addClass(Styles.navBtn)
            text = "Пройти тест"
            setOnAction {
                replaceWith(TestView::class)
            }
        }
        button {
            addClass(Styles.navBtn)
            text = "Мои результаты"
            setOnAction {
                openMyStatistics()
            }
        }

        button {
            addClass(Styles.navBtn)
            text = "Персональная статистика"
            setOnAction {
                replaceWith(StatisticsView::class)
            }
        }
        button {
            addClass(Styles.navBtn)
            text = "Все результаты"
            setOnAction {
                replaceWith(AllResultsView::class)
            }
        }
        button {
            addClass(Styles.navBtn)
            text = "Выйти"
            setOnAction {
                replaceWith(InitView::class)
            }
        }
    }


    override val root = psychologistRoot

    private fun openMyStatistics() {
        statisticsController.loadMyStatistics()
        find<PersonalStatistics>().openModal(block = true)
    }

}

