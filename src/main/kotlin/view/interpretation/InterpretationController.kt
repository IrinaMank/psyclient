package view.interpretation

import database.DbProvider
import tornadofx.Controller
import javax.xml.transform.Result

class InterpretationController : Controller() {

    fun getInterpretation(result: enity.Result): Boolean? {
        var workEffect = 0f
        result.time.apply {
            workEffect = this.sum() /  this.size
        }

        var workDegree = result.time[0] / workEffect
        var vinoslivost = result.time.last()/ workEffect

        if (UserData.id != null) {
            return true
        } else {
            return null
        }
    }
}