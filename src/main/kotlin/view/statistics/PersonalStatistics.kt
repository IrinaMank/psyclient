package view.statistics

import javafx.geometry.Pos
import tornadofx.*

class PersonalStatistics : View() {


    private val controller: StatisticsController by inject()

    override val root = vbox(10.0) {
        spacing = 10.0
        label {
            addClass(Styles.header)
            text = "Персональная статистика пользователя: ${controller.personName}"
        }
        tableview(controller.statisticsList) {
            columnResizePolicy = SmartResize.POLICY
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