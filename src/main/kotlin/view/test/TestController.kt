package view.test

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
    val test: TestView by inject()
    val result = observable<Result>("time")

    fun replace() {
        test.replaceWith(ResultView::class)
    }

    fun uploadResult(result: Result) {
        transaction {
            ResultEntry.new {
                this.userId = UserData.id!!//ToDo: remove !!
                this.result = result.time
                this.mistakes = result.mistakes
                this.date = DateTime()
            }
        }
        this.result.set(result)
    }

}