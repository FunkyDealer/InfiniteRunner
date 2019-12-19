package ipca.games.infiniterunner

import android.content.Context
import android.graphics.*
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import java.util.*

open class GameObject {


    lateinit var bitmap : Bitmap
    var game : Game? = null

    var position : Vector2
    var direction : Vector2
    val frameLengthInMilliseconds : Long = 100 //how long each frame lasts

    var currentFrame : Int = 0
    var currentAnimation : Int = 0


    // What time was it when we last changed frames
    var lastFrameChangeTime : Long = 0


    var frameSize : Vector2 = Vector2(0f,0f) //Width and Height of the object
    var frameCount : Int = 0 //How many animation frames it has
    var animationCount : Int = 0 //how many animations it has

    // A rectangle to define an area of the
    // sprite sheet that represents 1 frame
    var frameToDraw = RectF( //Frame to draw Rectangle
        0f,
        0f,
        frameSize.x,
        frameSize.y
    )

    var whereToDraw = RectF( //Where to draw Rectangle
        0f,
        0f,
        0f + frameSize.x,
        frameSize.y
    )

    var borderSize : Vector2

    var scalar = 1f //Object scaling

    lateinit var collider : Rect

    constructor(context: Context, game: Game?, position: Vector2) {
        this.game = game
        this.position = position
        direction = Vector2.Zero()

        borderSize = game!!.screenSize
    }

    open fun update(gameTime: GameTime) {

    }

    open fun draw(canvas : Canvas, paint : Paint) {

    }


    fun getCurrentFrame() {
        whereToDraw.set(
            position.x,
            position.y,
            position.x + frameSize.x,
            position.y + frameSize.y
        )


        val time = System.currentTimeMillis()
        if (time > lastFrameChangeTime + frameLengthInMilliseconds) {
            lastFrameChangeTime = time
            currentFrame++
            if (currentFrame >= frameCount) {

                currentFrame = 0
            }
        }

        //update the left and right values of the source of
        //the next frame on the spritesheet
        var left = currentFrame * frameSize.x
        var right = left + frameSize.x
        var top = currentAnimation * frameSize.y / animationCount
        var bottom = top + frameSize.y / animationCount

        frameToDraw.set(left, top, right, bottom)
    }

    fun SetUp() {
        whereToDraw.set(
            position.x,
            position.y,
            position.x + frameSize.x,
            position.y + frameSize.y
        )

        var left = currentFrame * frameSize.x
        var right = left + frameSize.x
        var top = currentAnimation * frameSize.y
        var bottom = top + frameSize.y / animationCount

        frameToDraw.set(left, top, right, bottom)

        bitmap = Bitmap.createScaledBitmap(bitmap,
            frameSize.x.toInt() * frameCount,
            frameSize.y.toInt(),
            false)
    }
}