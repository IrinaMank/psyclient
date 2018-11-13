package database

object DbProvider {
    private val db = MnpDatabase()

    fun getDb() = db
}