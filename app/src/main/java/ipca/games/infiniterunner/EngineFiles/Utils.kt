package ipca.games.infiniterunner.EngineFiles

import java.text.SimpleDateFormat
import java.util.*

fun dateToString(date: Date) : String {
    val format = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    val dateStr = format.format(date)
    return dateStr
}