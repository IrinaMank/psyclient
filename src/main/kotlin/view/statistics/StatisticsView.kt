package view.statistics

import javafx.scene.control.TextField
import tornadofx.*
import view.mainmenu.UserView

class StatisticsView : View() {

    private val controller: StatisticsController by inject()
    private var firstNameField: TextField by singleAssign()
    private var lastNameField: TextField by singleAssign()

    override val root =

        form {
            fieldset("User name") {
                field("First Name") {
                    firstNameField = textfield()
                }
                field("Last Name") {
                    lastNameField = textfield()
                }
            }

            button {
                text = "Watch statistics"
                setOnAction {
                    controller.loadPersonalStatistics(firstNameField.text, lastNameField.text)
                    find<PersonalStatistics>().openModal(block = true)
                }
            }

            button("back to main menu") {
                setOnAction {
                    replaceWith(UserView::class)
                }
            }
        }
}