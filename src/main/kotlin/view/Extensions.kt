package view

import enity.Interpretation
import enity.Result

fun Result.getInterpretation(): Interpretation {
    var workEffect = 0f
    this.time.apply {
        workEffect = this.sum() / this.size
    }

    val workability = this.time[0] / workEffect
    val persistence = this.time.last() / workEffect

    return Interpretation(workability, persistence)
}


fun Float.workabilityInText() =
        if (this > 1) {
            "Выше среднего"
        } else {
            "Ниже среднего"
        }

fun Float.persistenceInText() =
        if (this < 1) {
            "Выше среднего"
        } else {
            "Ниже среднего"
        }

fun Float.getString(): String ="${(this / 60000).toInt()}min ${(this /1000).toInt()}sec"

fun Float.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

enum class Sex(val inInt: Int) {
    FEMALE(1),
    MALE(0)
}
