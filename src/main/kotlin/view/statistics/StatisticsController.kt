package view.statistics

import database.DbProvider
import enity.Result
import tornadofx.Controller
import tornadofx.observable

class StatisticsController: Controller() {

    private val db = DbProvider.getDb()
    var statisticsList = mutableListOf<Result>().observable()
    var personName = ""

    fun loadPersonalStatistics(firstName: String, lastName: String) {
        personName = ("$firstName $lastName")
        statisticsList.setAll(db.getPersonalStatisticsByName(firstName, lastName))
    }

    fun loadMyStatistics() {
        UserData.user?.let {
            personName = ("${it.firstName} ${it.lastName}")
            statisticsList.setAll(db.getPersonalStatisticsById(it.id))
        }
    }

}