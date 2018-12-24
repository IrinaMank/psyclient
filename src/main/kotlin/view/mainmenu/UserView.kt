package view.mainmenu

import javafx.geometry.Pos
import view.test.TestView
import tornadofx.*
import view.help.HelpView
import view.interpretation.InterpretationView
import view.psychologist.AllResultsView
import view.statistics.PersonalStatistics
import view.statistics.StatisticsController
import view.statistics.StatisticsView

class UserView : View() {

    private val statisticsController: StatisticsController by inject()

    private val rootBox = vbox{}
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

    private val userRoot = vbox(10.0) {
        addClass(Styles.wrapper)
        button {
            addClass(Styles.navBtn)
            text = "Пройти тест"
            setOnAction {
                replaceWith(HelpView::class)
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
            text = "Выйти"
            setOnAction {
                UserData.user = null
                replaceWith(InitView::class)
            }
        }
    }

    override val root = rootBox

    override fun onDock() {
        super.onDock()
        if (UserData.user?.userType == 0) root.add(userRoot) else root.add(psychologistRoot)
    }

    override fun onUndock() {
        super.onUndock()
        userRoot.removeFromParent()
        psychologistRoot.removeFromParent()
    }
    private fun openMyStatistics() {
        statisticsController.loadMyStatistics()
        find<PersonalStatistics>().openModal(block = true)
    }

}

