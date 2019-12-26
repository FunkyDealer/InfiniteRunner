package ipca.games.infiniterunner.Collectables

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.graphics.toRect
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.R
import ipca.games.infiniterunner.RunnerGame

class refreshment : Collectables {


    constructor(context: Context, game: RunnerGame?, position: Vector2, screenSize : Vector2) : super(context, game, position, screenSize) {

        bitmap = BitmapFactory.decodeResource(context.resources,
            R.drawable.refreshment
        )

        frameCount = 1
        animationCount = 1
        scalar = 1f

        frameSize.x = ((bitmap.width.toFloat() / frameCount) * scalar)
        frameSize.y = ((bitmap.height.toFloat() / animationCount) * scalar)

        position.y -= frameSize.y

        SetUp()


        collider = whereToDraw.toRect()
    }

    override fun update(gameTime : GameTime, playerSpeed : Float) : Boolean {

        direction = Vector2.Left() * playerSpeed

        position += direction *  gameTime.Delta()


        getCurrentFrame()
        collider.set(whereToDraw.toRect())
        if (position.x < -frameSize.x) exists = false

        return exists
    }


    override fun draw(canvas : Canvas, paint : Paint) {

        canvas.drawBitmap(bitmap!!, frameToDraw.toRect(), whereToDraw, paint)

    }

}