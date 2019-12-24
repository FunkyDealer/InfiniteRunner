package ipca.games.infiniterunner.Collectables

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.Game
import java.util.*
import kotlin.collections.ArrayList

class CollectablesManager {



    var CollectablesList : MutableList<Collectables> = ArrayList<Collectables>()
    val game : Game
    var timer : Float = 0f
    var generator = Random()
    val context : Context?


    constructor(context: Context?, screenSize : Vector2, game: Game) {

        this.game = game
        this.context = context

    }

    fun update(gameTime: GameTime, playerSpeed : Float) {
        timer += gameTime.Delta()

        if (timer > 4) { //
            for (i in 0 until (generator.nextInt(3) + 1)) {
                var pos = Vector2(game.screenSize.x, (game.screenSize.y - game.floorSize.y) * generator.nextDouble().toFloat())
                var rng = generator.nextBoolean()
               if (rng) CollectablesList.add(refreshment(context!!, game, pos))
                else CollectablesList.add(Sugar(context!!, game, pos))
            }
            timer = 0f
        }

        for (c in CollectablesList) {
            if (c.collider.intersect(game.player.collider)) {
                c.exists = false
                if (c is refreshment && game.thirstBar.thirstValue < 10) {
                    game.thirstBar.thirstValue++
                    game.score += 50
                    game.thirstBar.timer = 0f
                }
                else if (c is Sugar && game.thirstBar.thirstValue > 0) {
                    game.thirstBar.thirstValue--
                    if (game.score > 0) game.score -= 25
                }
            }
        }

        for (c in CollectablesList.reversed())
        {
            if (!c.update(gameTime, playerSpeed)) {
                CollectablesList.remove(c)
            }
        }

    }

    fun draw(canvas : Canvas, paint : Paint){

        for (c in CollectablesList)
        {
            c.draw(canvas, paint)
        }

    }
}