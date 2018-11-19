package view.statistics

import database.DbProvider
import enity.Result
import tornadofx.Controller

class StatisticsController: Controller() {

    private val db = DbProvider.getDb()
    var statisticsList = listOf<Result>()

    fun loadPersonalStatistics(firstName: String, lastName: String) {
        statisticsList = db.getPersonalStatistics(firstName, lastName)
    }

}