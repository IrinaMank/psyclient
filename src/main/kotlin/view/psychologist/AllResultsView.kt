package view.psychologist

import database.DbProvider
import javafx.geometry.Pos
import javafx.scene.control.TextField
import tornadofx.*
import view.mainmenu.UserView
import view.getInterpretation
import view.persistenceInText
import view.workabilityInText

class AllResultsView : View() {

    private val allResults = DbProvider.getDb().getAllStatistics().observable()

    override val root = vbox(10.0) {
        tableview(allResults) {
            column("Имя", String::class)  {
                value { it.value.userName }
            }
            column("Время 1 табл.", String::class)  {
                value { it.value.result.time[0] }
            }
            column("Время 2 табл.", String::class)  {
                value { it.value.result.time[1] }
            }
            column("Время 3 табл.", String::class)  {
                value { it.value.result.time[2] }
            }
            column("Время 4 табл.", String::class)  {
                value { it.value.result.time[3] }
            }
            column("Время 5табл.", String::class)  {
                value { it.value.result.time[4] }
            }
            column("Дата прохождения", String::class)  {
                value { it.value.result.date }
            }
            column("Врабатываемость", String::class)  {
                value { it.value.result.getInterpretation().workability.workabilityInText() }
            }
            column("Устойчивость", String::class)  {
                value { it.value.result.getInterpretation().persistence.persistenceInText() }
            }

        }

        button("Назад") {
            addClass(Styles.navBtn)
            setOnAction {
                replaceWith(UserView::class)
            }
        }
    }
}