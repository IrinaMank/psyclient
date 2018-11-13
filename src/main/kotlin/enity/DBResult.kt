package enity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Results: IntIdTable() {
    val userId = integer("userId")
    val time = float("time")
    val mistakes = integer("mistakes")
    val date = date("date")
}

class ResultEntry(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<ResultEntry>(Results)
    var userId by Results.userId
    var result by Results.time
    var mistakes by Results.mistakes
    var date by Results.date
}