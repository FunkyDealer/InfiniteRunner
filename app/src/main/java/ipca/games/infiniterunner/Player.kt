package ipca.games.infiniterunner

import android.R.attr.*
import android.content.Context
import android.graphics.*
import android.graphics.RectF
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.core.graphics.toRect
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import java.text.FieldPosition


class Player : GameObject {

    var speed : Float = 300f
    var jumpSpeed : Float = 200f

    private var maxX = 0f
    private var minX = 0f
    private var maxY = 0f
    private var minY = 0f

    var nextPos : Vector2 = Vector2.Zero()

    var jumpForce : Vector2 = Vector2.Zero()

    var colliding : Boolean = false
    var jumpTimer = 0f
    var jumping = true
    var falling = false

    enum class Animation {
        RUNNING(0),
        JUMPING(1);

        var Nr : Int

        constructor(nr: Int) {
            this.Nr = nr
        }
    }


    constructor(context: Context, game: Game, position: Vector2) : super(context, game, position) {

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        frameCount = 2
        animationCount = 2
        scalar = 1f

        frameSize.x = ((bitmap.width.toFloat() / frameCount) * scalar)
        frameSize.y = ((bitmap.height.toFloat() / animationCount) * scalar)

        currentAnimation = Animation.JUMPING.Nr

        SetUp() //Sets up where to draw and What to draw

        maxY = borderSize.y
        maxX = borderSize.x



        collider = whereToDraw.toRect()
    }

    override fun update(gameTime: GameTime) {

        direction = Vector2.Zero() //Direction is reset each frame

        direction += game!!.gravity + jumpForce //Direction is the addition of all forces that act on the object
        direction.Normalize()

        nextPos = position + direction * jumpSpeed * gameTime.Delta()

        if (nextPos.y > minY && nextPos.y < maxY - frameSize.y && nextPos.x > minX && nextPos.x < maxX - frameSize.x) //Check if next pos is still inside The SCreen
        {
            if (nextPos.y < maxY - (frameSize.y + game!!.floorManager.floorList[0].frameSize.y)) { //Check if nextPos is not inside the floor
                position = nextPos
            }
            else {
                falling = false
            }
        }
        //if (canMove) { position = nextPos; nextPos = position }

        if (position.y < minY) position.y = minY  //bitmap.height
        if (position.y > maxY - frameSize.y) position.y = maxY - frameSize.y //Works
        if (position.x < minX) position.x = minX + bitmap.width
        if (position.x > maxX - frameSize.x) position.x = maxX - frameSize.x //works


        if (jumping || falling) {
            currentAnimation = Animation.JUMPING.Nr
        } else currentAnimation = Animation.RUNNING.Nr

        getCurrentFrame()
        collider.set(whereToDraw.toRect())
/*
        if (jumpForce.y < 0) {
        jumpForce += Vector2.Down()
        } else jumpForce = Vector2.Zero()
*/

        if (jumping) { //Jumping Logic
            jumpTimer += gameTime.Delta()
            if (jumpTimer > 0.5f) { //You can jump for 0.5f second max
                falling = true
                jumping = false
                jumpTimer = 0f
                jumpForce = Vector2.Zero()
            }
        }
    }

    override fun draw(canvas : Canvas, paint : Paint) {

        canvas.drawBitmap(bitmap!!, frameToDraw.toRect(), whereToDraw, paint) //Player

        if (colliding) paint.color = Color.RED
        else paint.color = Color.GREEN

        //paint.color = Color.GREEN
        paint.textSize = 40f

        canvas.drawText("dir: " + direction, game!!.screenSize!!.x / 2f, 110.0f, paint)
        canvas.drawText("jump: " + jumpForce, game!!.screenSize!!.x / 2f, 150.0f, paint)
        canvas.drawText("jumping: " + jumping, game!!.screenSize!!.x / 2f, 190.0f, paint)
        canvas.drawText("falling: " + falling, game!!.screenSize!!.x / 2f, 230.0f, paint)
        canvas.drawText("bitmap: " + frameSize.toString(), game!!.screenSize!!.x / 2f, 270.0f, paint)

    }

        fun MotionDown() {
            if (!falling) {
                jumping = true
                jumpForce = game!!.gravity * -2f
            }
            //if (!inAir)
            //jumpForce = game!!.gravity * -5f
        }

    fun MotionUp() {
        jumping = false
        falling = true
        jumpForce = Vector2.Zero()
        jumpTimer = 0f
    }

}