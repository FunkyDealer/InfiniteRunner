package ipca.games.infiniterunner.GameObjects

import android.content.Context
import android.graphics.*
import androidx.core.graphics.toRect
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.R
import ipca.games.infiniterunner.RunnerGame


class Player : GameObject {

    var speed : Float = 300f
    var jumpSpeed : Float = 200f

    private var maxX = 0f
    private var minX = 0f
    private var maxY = 0f
    private var minY = 0f

    var nextPos : Vector2 = Vector2.Zero()

    var jumpForce : Vector2 = Vector2.Zero()

    var jumpTimer = 0f
    var jumping = true
    var doubleJumping = false
    var falling = false
    var doubleJumps = 0
    var maxDoubleJumps = 1
    var startingPos : Vector2

    enum class Animation {
        RUNNING(0),
        JUMPING(1);

        var Nr : Int

        constructor(nr: Int) {
            this.Nr = nr
        }
    }


    constructor(context: Context, game: RunnerGame, position: Vector2, screenSize : Vector2) : super(context, game, position, screenSize) {

        bitmap = BitmapFactory.decodeResource(context.resources,
            R.drawable.player
        )
        startingPos = position

        frameCount = 2
        animationCount = 2
        scalar = 1f

        frameSize.x = ((bitmap.width.toFloat() / frameCount) * scalar)
        frameSize.y = ((bitmap.height.toFloat() / animationCount) * scalar)

        currentAnimation = Animation.JUMPING.Nr

        SetUp() //Sets up where to Draw and What to Draw

        maxY = borderSize.y
        maxX = borderSize.x



        collider = whereToDraw.toRect()
    }

    fun reboot() {

        currentAnimation = Animation.JUMPING.Nr
        position = startingPos
        direction = Vector2.Zero()

    }

    override fun Update(gameTime: GameTime) {

        direction = Vector2.Zero() //Direction is reset each frame

        direction += game!!.gravity + jumpForce //Direction is the addition of all forces that act on the object
        direction.Normalize()

        nextPos = position + direction * jumpSpeed * gameTime.Delta()


            if (nextPos.y < maxY - (frameSize.y + game!!.floorManager.floorList[0].frameSize.y)) { //Check if nextPos is not inside the floor
                position = nextPos
            }
            else {
                if (falling) falling = false
                if (doubleJumps > 0) doubleJumps = 0
            }
        //if (canMove) { position = nextPos; nextPos = position }

        if (position.y > maxY - frameSize.y) position.y = maxY - frameSize.y //Works
        if (position.x < minX) position.x = minX + bitmap.width
        if (position.x > maxX - frameSize.x) position.x = maxX - frameSize.x //works


        if (jumping || falling || doubleJumping) {
            currentAnimation = Animation.JUMPING.Nr
        } else currentAnimation = Animation.RUNNING.Nr

        getCurrentFrame()
        collider.set(whereToDraw.toRect())
/*
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
         if (doubleJumping && jumpForce.y < 0) {
            jumpForce += Vector2.Down()
        } else if (doubleJumping)  {
            jumpForce = Vector2.Zero()
            doubleJumping = false
            falling = true
        }


    }

    override fun Draw(canvas : Canvas, paint : Paint, gameTime: GameTime) {

        canvas.drawBitmap(bitmap, frameToDraw.toRect(), whereToDraw, paint) //Player
        paint.color = Color.GREEN

        //paint.color = Color.GREEN
        paint.textSize = 40f

        canvas.drawText("dir: " + direction, borderSize.x / 2f, 110.0f, paint)
        canvas.drawText("jump: " + jumpForce, borderSize.x / 2f, 150.0f, paint)
        canvas.drawText("jumping: " + jumping, borderSize.x / 2f, 190.0f, paint)
        canvas.drawText("falling: " + falling, borderSize.x / 2f, 230.0f, paint)
        canvas.drawText("bitmap: " + frameSize.toString(), borderSize.x / 2f, 270.0f, paint)

    }

        fun MotionDown() {
            if (!falling) {
                jumping = true
                jumpForce = game!!.gravity * -2f
            }
            if (!jumping && falling && !doubleJumping && doubleJumps < maxDoubleJumps) {
                doubleJumping = true
                falling = false
                jumpForce = game!!.gravity * -3f
                doubleJumps++
            }

        }

    fun MotionUp() {
        if (!doubleJumping)
            jumping = false
            falling = true
            jumpForce = Vector2.Zero()
            jumpTimer = 0f
    }

}