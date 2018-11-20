package view.mainmenu

import javafx.geometry.Pos
import view.test.TestView
import tornadofx.*
import view.interpretation.InterpretationView
import view.psychologist.AllResultsView
import view.statistics.PersonalStatistics
import view.statistics.StatisticsController
import view.statistics.StatisticsView

class UserView : View() {

    private val statisticsController: StatisticsController by inject()
    private val psychologistRoot = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        button {
            text = "Attempt view.test"
            setOnAction {
                replaceWith(TestView::class)
            }
        }
        button {
            text = "View my results"
            setOnAction {
                openMyStatistics()
            }
        }

        button {
            text = "View personal statistics"
            setOnAction {
                replaceWith(StatisticsView::class)
            }
        }
        button {
            text = "View interpretations"
            setOnAction {
                replaceWith(AllResultsView::class)
            }
        }
    }

    private val userRoot = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER
        button {
            text = "Attempt view.test"
            setOnAction {
                replaceWith(TestView::class)
            }
        }
        button {
            text = "View my results"
            setOnAction {
                openMyStatistics()
            }
        }
    }

    override val root = if (UserData.user?.userType == 0) userRoot else psychologistRoot

    private fun openMyStatistics() {
        statisticsController.loadMyStatistics()
        find<PersonalStatistics>().openModal(block = true)
    }

}

