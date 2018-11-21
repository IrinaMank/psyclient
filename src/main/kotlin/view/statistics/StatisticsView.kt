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
            fieldset("Имя пользователя") {
                field("Фамилия") {
                    lastNameField = textfield()
                }
                field("Имя") {
                    firstNameField = textfield()
                }
            }

            button {
                addClass(Styles.navBtn)
                text = "Посмотреть статистику"
                setOnAction {
                    controller.loadPersonalStatistics(firstNameField.text, lastNameField.text)
                    find<PersonalStatistics>().openModal(block = true)
                }
            }

            button("Назад") {
                addClass(Styles.navBtn)
                setOnAction {
                    replaceWith(UserView::class)
                }
            }
        }
}