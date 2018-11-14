package view.interpretation

import database.DbProvider
import tornadofx.Controller

class InterpretationController : Controller() {

    fun getInterpretation(): Boolean? {
        if (UserData.id != null) {
            return true
        } else {
            return null
        }
    }
}