package ipca.games.infiniterunner.GameObjects

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.RunnerGame

class FloorManager {


    var floorList : MutableList<Floor> = ArrayList<Floor>()
    val game : RunnerGame


    constructor(context: Context?, screenSize : Vector2, game: RunnerGame) {

    this.game = game

        for (i in 0 until 20) {
            floorList.add(
                Floor(
                    context!!,
                    game,
                    Vector2(i.toFloat(), screenSize.y),
                    screenSize
                )
            )
        }
    }

    fun update(gameTime: GameTime, playerSpeed : Float) {

        for (f in floorList)
        {
            f.update(gameTime, playerSpeed)
        }

    }

    fun draw(canvas : Canvas, paint : Paint){

        for (f in floorList)
        {
            f.draw(canvas, paint)
        }

    }






}