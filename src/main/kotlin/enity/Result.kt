package enity

import org.joda.time.DateTime
import java.util.*

data class Result(
        val time: ArrayList<Float> = ArrayList(),
        val date: DateTime = DateTime()
)