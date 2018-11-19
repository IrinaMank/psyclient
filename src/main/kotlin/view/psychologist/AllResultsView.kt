package view.psychologist

import database.DbProvider
import javafx.geometry.Pos
import javafx.scene.control.TextField
import tornadofx.*
import view.UserView
import view.getInterpretation
import view.persistenceInText
import view.statistics.PersonalStatistics
import view.statistics.StatisticsController
import view.workabilityInText

class AllResultsView : View() {

    private val allResults = DbProvider.getDb().getAllStatistics().observable()
    private var firstNameField: TextField by singleAssign()
    private var lastNameField: TextField by singleAssign()

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER

        tableview(allResults) {
            column("UserName", String::class)  {
                value { it.value.userName }
            }
            column("Time 1", String::class)  {
                value { it.value.result.time[0] }
            }
            column("Time 2", String::class)  {
                value { it.value.result.time[1] }
            }
            column("Time 3", String::class)  {
                value { it.value.result.time[2] }
            }
            column("Time 4", String::class)  {
                value { it.value.result.time[3] }
            }
            column("Time 5", String::class)  {
                value { it.value.result.time[4] }
            }
            column("Date", String::class)  {
                value { it.value.result.date }
            }
            column("Workability", String::class)  {
                value { it.value.result.getInterpretation().workability.workabilityInText() }
            }
            column("Persistence", String::class)  {
                value { it.value.result.getInterpretation().persistence.persistenceInText() }
            }

        }
    }
}