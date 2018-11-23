package view.test

import database.DbProvider
import enity.Interpretation
import enity.Result
import tornadofx.Controller

class TestController: Controller()  {
    private val db = DbProvider.getDb()
    val test: TestView by inject()
    var resultObservable = Result()

    fun replace() {
        test.replaceWith(ResultView::class)
    }

    fun addTimeToResult(time: Float) {
        resultObservable.time.add(time)
    }

    fun uploadResult(result: Result) {
        db.uploadResult(result)
        this.resultObservable = result
    }

    fun getInterpretation(): Interpretation? {
        var workEffect = 0f
        resultObservable.time.apply {
            workEffect = this.sum() /  this.size
        }

        val workability = resultObservable.time[0] / workEffect
        val persistence = resultObservable.time.last()/ workEffect

        return Interpretation(workability, persistence)
    }

}