package ipca.games.infiniterunner

import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Scene
import ipca.games.infiniterunner.EngineFiles.Vector2

class GameOverScreen : Scene {

    var timer : Float
    var reboot : Boolean


    constructor(game: Game, screenSize: Vector2, name: String, playing: Boolean) : super(game,screenSize,name,playing) {

        timer = 0f
        reboot = false

    }

    override fun Update(gameTime: GameTime) {
        super.Update(gameTime)
        timer += gameTime.Delta() //Timer to allow restarting

        if (!reboot && timer > 1.5f) {
            reboot = true
            timer = 0f
        }
    }

    override fun Draw(canvas: Canvas, paint: Paint, gameTime: GameTime) {
        super.Draw(canvas, paint, gameTime)

        paint.textSize = 100f

        canvas.drawText("GAME OVER", screenSize.x / 2, screenSize.y / 2, paint)
        canvas.drawText("YOUR SCORE: " + game.scoreManager, screenSize.x / 2, screenSize.y / 2 + 100, paint)

        if (reboot) canvas.drawText("tap to restart", screenSize.x / 2, screenSize.y / 2 + 200, paint)

    }

    override fun MotionEventUp() {
        super.MotionEventUp()



    }

    override fun MotionEventDown() {
        super.MotionEventDown()

        if (reboot) {
            timer = 0f
            reboot = false
            game.runnerGame.reboot(this)


        }
    }

}