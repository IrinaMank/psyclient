package enity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Results: IntIdTable() {
    val user =  reference("user", Users)
    val time1 = float("time1")
    val time2 = float("time2")
    val time3 = float("time3")
    val time4 = float("time4")
    val time5 = float("time5")
    val date = date("date")
}

class ResultEntry(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<ResultEntry>(Results)
    var user by UserEntry referencedOn Results.user
    var time1 by Results.time1
    var time2 by Results.time2
    var time3 by Results.time3
    var time4 by Results.time4
    var time5 by Results.time5
    var date by Results.date
}