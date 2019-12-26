package ipca.games.infiniterunner

import ipca.games.infiniterunner.EngineFiles.dateToString
import java.util.*

class Score {

    var score : Int
    var date : String


    constructor(score: Int) {
        this.score = score
        this.date = dateToString(Date())

    }

    constructor(score: Int, date : Date) {
        this.score = score
        this.date = dateToString(date)
    }
}