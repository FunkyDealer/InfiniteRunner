package ipca.games.infiniterunner.GameObjects.Collectables

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.RunnerGame
import java.util.*

class CollectablesManager {



    var CollectablesList : MutableList<Collectables> = ArrayList<Collectables>()
    val game : RunnerGame
    var timer : Float = 0f
    var generator = Random()
    val context : Context?
    val screenSize : Vector2


    constructor(context: Context?, screenSize : Vector2, game: RunnerGame) {

        this.game = game
        this.context = context
        this.screenSize = screenSize

    }

    fun update(gameTime: GameTime, playerSpeed : Float) {
        timer += gameTime.Delta()

        if (timer > 4) { //
            for (i in 0 until (generator.nextInt(3) + 1)) {
                var pos = Vector2(screenSize.x, (screenSize.y - game.floorSize.y) * generator.nextDouble().toFloat())
                var rng = generator.nextBoolean()
               if (rng) CollectablesList.add(refreshment(context!!, game, pos, screenSize))
                else CollectablesList.add(Sugar(context!!, game, pos, screenSize))
            }
            timer = 0f
        }

        for (c in CollectablesList) {
            if (c.collider.intersect(game.player.collider)) {
                c.exists = false
                if (c is refreshment && game.thirstBar.thirstValue < game.thirstBar.initialValue) {
                    game.thirstBar.thirstValue++
                    game.ScoreMan().addCurrentScore(50)
                    game.thirstBar.timer = 0f
                }
                else if (c is Sugar && game.thirstBar.thirstValue > 0) {
                    game.thirstBar.thirstValue--
                    if (game.ScoreMan().getCurrentScore() > 0) game.ScoreMan().subtractCurrentScore(25)
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

    fun draw(canvas : Canvas, paint : Paint, gameTime: GameTime){

        for (c in CollectablesList)
        {
            c.Draw(canvas, paint, gameTime)
        }

    }


    fun reboot() {
        timer = 0f
        CollectablesList.clear()
    }


}