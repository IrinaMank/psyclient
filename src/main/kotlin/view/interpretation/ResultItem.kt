package view.interpretation

import enity.Result
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.Scoped

class ResultItem(result: Result) {
    val resultProperty = SimpleObjectProperty(result)
}