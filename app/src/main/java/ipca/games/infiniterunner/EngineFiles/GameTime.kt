package ipca.games.infiniterunner.EngineFiles

import java.time.Duration

class GameTime {


    private var elapsedTime : Duration
    private var totalTime : Duration


    constructor() {
        elapsedTime = Duration.ZERO
         totalTime = Duration.ZERO

    }

   internal fun update(elapsed : Duration) {
        elapsedTime = elapsed
        totalTime += elapsed
    }

   val ElapsedGameTime : Duration
       get() = elapsedTime

    val TotalGameTime : Duration
        get() = totalTime


    fun Delta() : Float {
        return (elapsedTime.toMillis() / 1000f)
    }
}