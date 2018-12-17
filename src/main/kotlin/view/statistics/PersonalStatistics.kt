package view.statistics

import javafx.geometry.Pos
import org.joda.time.format.DateTimeFormat
import tornadofx.*
import view.getString

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
            column("Таблица 1", String::class)  {
                value { it.value.time[0].getString() }
            }
            column("Таблица 2", String::class)  {
                value { it.value.time[1].getString() }
            }
            column("Таблица 3", String::class)  {
                value { it.value.time[2].getString() }
            }
            column("Таблица 4", String::class)  {
                value { it.value.time[3].getString() }
            }
            column("Таблица 5", String::class)  {
                value { it.value.time[4].getString() }
            }
            column("Дата", String::class)  {
                value {
                    it.value.date.toString(DateTimeFormat.forPattern("dd MMM YYYY"))
                }
            }
        }
    }
}