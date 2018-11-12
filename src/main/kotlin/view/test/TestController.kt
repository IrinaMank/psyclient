package view.test

import view.LoginView
import tornadofx.Controller

class TestController: Controller()  {
    val login: LoginView by inject()
    val test: TestView by inject()

    fun replace() {
        test.replaceWith(LoginView::class)
    }

}