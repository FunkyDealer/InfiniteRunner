package ipca.games.infiniterunner

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.graphics.toRect
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2

class ThirstBar : GameObject {


    var thirstValue : Int
    var timer : Float = 0f

    constructor(context: Context, game: Game?, position: Vector2, thirstValue: Int) : super(context, game, position) {
        this.thirstValue = thirstValue

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.water)

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


        if (thirstValue < 1 && game!!.playing) {
            game!!.gameOver()
        }

    }

    override fun draw(canvas : Canvas, paint : Paint) {

        for (i in 0 until thirstValue) {
            updateWhere2Draw(i)
            canvas.drawBitmap(bitmap!!, frameToDraw.toRect(), whereToDraw, paint) //Player
        }

    }

    fun updateWhere2Draw(i : Int) {
        //position.x += (i * frameSize.x)

        whereToDraw.set(
            position.x + (i * frameSize.x),
            position.y,
            position.x + frameSize.x + (i * frameSize.x),
            position.y + frameSize.y
        )
    }

}