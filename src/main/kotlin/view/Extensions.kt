package view

import enity.Interpretation
import enity.Result

fun Result.getInterpretation(): Interpretation {
    var workEffect = 0f
    this.time.apply {
        workEffect = this.sum() / this.size
    }

    val workability = this.time[0] / workEffect//ToDo: здесь падает понулевому размеру
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