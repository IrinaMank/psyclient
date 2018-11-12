package enity

data class User(
        val login: String = "",
        val password: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val age: Int = 0,
        val sex: Int = 0,
        val employment: String = "",
        val userType: Int = 0
)