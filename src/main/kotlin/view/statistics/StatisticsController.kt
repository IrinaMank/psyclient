package view.statistics

import database.DbProvider
import enity.Result
import tornadofx.Controller
import tornadofx.observable

class StatisticsController: Controller() {

    private val db = DbProvider.getDb()
    var statisticsList = mutableListOf<Result>().observable()

    fun loadPersonalStatistics(firstName: String, lastName: String) {
        statisticsList.setAll(db.getPersonalStatisticsByName(firstName, lastName))
    }

    fun loadMyStatistics() {
        UserData.user?.let {
            statisticsList.setAll(db.getPersonalStatisticsById(it.id))
        }
    }

}