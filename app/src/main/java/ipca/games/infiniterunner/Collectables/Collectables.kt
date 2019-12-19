package ipca.games.infiniterunner.Collectables

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.Game
import ipca.games.infiniterunner.GameObject

open class Collectables : GameObject {

    var exists : Boolean

    constructor(context: Context, game: Game?, position: Vector2) : super(context, game, position) {

    exists = true
    }


   open fun update(gameTime : GameTime, playerSpeed : Float) : Boolean {


       return exists
   }


    override fun draw(canvas : Canvas, paint : Paint) {

    }
}