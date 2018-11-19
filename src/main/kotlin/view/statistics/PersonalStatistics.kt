package view.statistics

import enity.Result
import javafx.geometry.Pos
import tornadofx.*
import view.UserView
import view.interpretation.InterpretationView

class PersonalStatistics : View() {


    private val controller: StatisticsController by inject()

    override val root = vbox(10.0) {
        prefWidth = 800.0
        prefHeight = 600.0
        alignment = Pos.CENTER

        tableview(controller.statisticsList.observable()) {
            column("Time 1", String::class)  {
                value { it.value.time[0] }
            }
            column("Time 2", String::class)  {
                value { it.value.time[1] }
            }
            column("Time 3", String::class)  {
                value { it.value.time[2] }
            }
            column("Time 4", String::class)  {
                value { it.value.time[3] }
            }
            column("Time 5", String::class)  {
                value { it.value.time[4] }
            }
            column("Date", String::class)  {
                value { it.value.date }
            }
        }
    }
}