package view.test

import database.DbProvider
import enity.Result
import enity.ResultEntry
import javafx.beans.value.ObservableObjectValue
import javafx.collections.FXCollections
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import tornadofx.Controller
import tornadofx.observable
import view.ResultView
class TestController: Controller()  {
    private val db = DbProvider.getDb()
    val test: TestView by inject()
    val result = Result()
    val resultObservable = observable<Result>("time")

    fun replace() {
        test.replaceWith(ResultView::class)
    }

    fun addTimeToResult(time: Float) {
        result.time.add(time)
    }

    fun uploadResult(result: Result) {
        db.uploadResult(result)
        this.resultObservable.set(result)
    }

}