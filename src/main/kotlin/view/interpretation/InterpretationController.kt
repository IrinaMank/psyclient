package view.interpretation

import database.DbProvider
import tornadofx.Controller

class InterpretationController : Controller() {

    fun getInterpretation(): Boolean? {
        if (UserData.id != null) {
            val average = DbProvider.getDb().getAverageResult()
            val userResult = DbProvider.getDb().getUserAverage(UserData.id!!)
            return userResult.mistakes <= average.mistakes
        } else {
            return null
        }
    }
}