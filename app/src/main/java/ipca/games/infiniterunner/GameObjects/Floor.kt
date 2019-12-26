package ipca.games.infiniterunner.GameObjects

import android.content.Context
import android.graphics.*
import androidx.core.graphics.toRect
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.R
import ipca.games.infiniterunner.RunnerGame

class Floor : GameObject {

    var colliding = false


    constructor(context: Context, game: RunnerGame?, position: Vector2, screenSize : Vector2) : super(context, game, position, screenSize) {
        bitmap = BitmapFactory.decodeResource(context.resources,
            R.drawable.floor
        )

        frameCount = 1
        animationCount = 1

        frameSize.x = ((bitmap.width.toFloat() / frameCount) * scalar)
        frameSize.y = ((bitmap.height.toFloat() / animationCount) * scalar)

        position.x *= frameSize.x
        position.y -= frameSize.y

        SetUp()


        collider = whereToDraw.toRect()
    }


    fun update(gameTime: GameTime, playerSpeed : Float) {

        direction.x -= playerSpeed * gameTime.Delta()

        position += direction

        if (position.x < -frameSize.x) {
            position.x = (game!!.floorManager.floorList.size - 1) * frameSize.x
        }

        getCurrentFrame()

        collider.set(whereToDraw.toRect())
        direction = Vector2.Zero()
        colliding = false
    }

    override fun draw(canvas : Canvas, paint : Paint) {

        if (colliding) paint.alpha = 127
        else paint.alpha = 255


        canvas.drawBitmap(bitmap, frameToDraw.toRect(), whereToDraw, paint) //Draw floor
    }

}