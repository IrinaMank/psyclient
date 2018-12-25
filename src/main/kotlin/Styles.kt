import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType
import javafx.scene.text.TextAlignment
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        // Define our styles
        val wrapper by cssclass()
        val header by cssclass()
        val navBtn by cssclass()
        val mistakeBtn by cssclass()
        val normalBtn by cssclass()

        // Define our colors
        val dangerColor = c("#a94442")
        val hoverColor = c("#d49942")
    }

    init {
        wrapper {
            padding = box(10.px)
            spacing = 10.px
            alignment = Pos.CENTER
            prefWidth = 800.0.px
            prefHeight = 600.0.px
        }

        label {
            padding = box(5.px, 10.px)
            maxWidth = 800.0.px
            wrapText = true

            add(header) {
                fontSize = 22.px
                textAlignment = TextAlignment.CENTER
            }
        }

        button {
            add(navBtn)  {
                prefWidth = 200.px
            }
        }

        button {
            add(mistakeBtn)  {
                prefWidth = 60.px
                prefHeight = 60.px
                backgroundColor += c("#FF0000")
            }
        }


        button {
            add(normalBtn)  {
                prefWidth = 200.px
                backgroundColor += c("#DCDCDC")
            }
        }

        tableView {
            columnHAlignment = HPos.CENTER
            //col = SmartResize.POLICY
        }
    }
}