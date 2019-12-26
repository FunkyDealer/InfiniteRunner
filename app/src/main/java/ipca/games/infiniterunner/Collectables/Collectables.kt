package ipca.games.infiniterunner.Collectables

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.GameObjects.GameObject
import ipca.games.infiniterunner.RunnerGame

open class Collectables : GameObject {

    var exists : Boolean

    constructor(context: Context, game: RunnerGame?, position: Vector2, screenSize : Vector2) : super(context, game, position, screenSize) {

    exists = true
    }


   open fun update(gameTime : GameTime, playerSpeed : Float) : Boolean {


       return exists
   }


    override fun draw(canvas : Canvas, paint : Paint) {

    }
}