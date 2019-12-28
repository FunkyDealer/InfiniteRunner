package ipca.games.infiniterunner.GameObjects

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.graphics.toRect
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.R
import ipca.games.infiniterunner.RunnerGame

class ThirstBar : GameObject {


    var thirstValue : Int
    var timer : Float = 0f
    var initialValue : Int

    constructor(context: Context, game: RunnerGame?, position: Vector2, thirstValue: Int, screenSize : Vector2) : super(context, game, position, screenSize) {
        this.thirstValue = thirstValue
        this.initialValue = thirstValue

        bitmap = BitmapFactory.decodeResource(context.resources,
            R.drawable.water
        )

        frameCount = 1
        animationCount = 1
        scalar = 1f

        frameSize.x = ((bitmap.width.toFloat() / frameCount) * scalar)
        frameSize.y = ((bitmap.height.toFloat() / animationCount) * scalar)

        SetUp()
    }

    override fun update(gameTime : GameTime) {
        timer += gameTime.Delta()

        if (timer > 6f) {
            if (thirstValue > 0) thirstValue--
            timer = 0f
        }


        if (thirstValue < 1 && game!!.updating) {
            game!!.gameOver()
        }

    }

    override fun draw(canvas : Canvas, paint : Paint) {

        for (i in 0 until thirstValue) {
            updateWhere2Draw(i)
            canvas.drawBitmap(bitmap, frameToDraw.toRect(), whereToDraw, paint) //Player
        }
    }

    fun updateWhere2Draw(i : Int) {
        whereToDraw.set(
            position.x + (i * frameSize.x),
            position.y,
            position.x + frameSize.x + (i * frameSize.x),
            position.y + frameSize.y
        )
    }

    fun reboot() {
        timer = 0f
        thirstValue = initialValue

    }

}