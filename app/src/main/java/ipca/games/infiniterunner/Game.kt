package ipca.games.infiniterunner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import ipca.games.infiniterunner.Collectables.CollectablesManager
import ipca.games.infiniterunner.EngineFiles.GameActivity
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.GameView
import ipca.games.infiniterunner.EngineFiles.Vector2
import java.util.*

class Game : SurfaceView {

    var player : Player
    var floorManager : FloorManager

    var score : Int = 0
    var screenSize : Vector2
    public var gravity : Vector2 = Vector2(0f, 5f)
    var fps : Long = 0

    var pressedDown : Boolean = false
    var thirstBar : ThirstBar
    var floorSize : Vector2
    var collectables : CollectablesManager
    var level : Int
    var levelTimer : Float

    constructor(context: Context?, screenSize : Vector2) : super(context) {

        this.screenSize = screenSize
        player = Player(context!!, this, Vector2(screenSize.x / 6, 300f))
        floorManager = FloorManager(context, screenSize, this)
        thirstBar = ThirstBar(context, this, Vector2(0f,0f), 10)
        floorSize = floorManager.floorList[0].frameSize
        collectables = CollectablesManager(context, screenSize, this)
        level = 1
        levelTimer = 0f
    }

    /*
     * This is the Update Function, where objects are updated
     */
    fun update(gameTime: GameTime) {
        levelTimer += gameTime.Delta()
        if (levelTimer > 30) {
            level++
            levelTimer = 0f
        }

        when (gameTime.ElapsedGameTime.toMillis() > 0) {
            (true) -> fps = 1000 / gameTime.ElapsedGameTime.toMillis()
            (false) -> fps = 0
        }


        player.update(gameTime)
        floorManager.update(gameTime, player.speed * (1 + (level * 0.1f)))
        thirstBar.update(gameTime)
        collectables.update(gameTime, player.speed * (1 + (level * 0.1f)))


    }


    /*
    * This is the Draw Function, where Objects are Drawn on screen
    */
    fun draw(canvas : Canvas, paint : Paint, gameTime: GameTime) {
        canvas.drawColor(Color.BLACK)

        player.draw(canvas, paint)
        floorManager.draw(canvas, paint)
        thirstBar.draw(canvas, paint)
        collectables.draw(canvas, paint)

        paint.color = Color.GREEN
        paint.textSize = 50f

        canvas.drawText("fps: " + fps, 0f, 60.0f, paint)
        canvas.drawText("gametime: " + gameTime.Delta().toString(), screenSize.x / 2f, 310.0f, paint)
        canvas.drawText("pressing Down: " + pressedDown, screenSize.x / 2f, 360.0f, paint)
        canvas.drawText("Level: " + level, screenSize.x - 300, screenSize.y - 50, paint)

    }

/*
* Place here Motion Events
*/
    fun MotionEventUp() {
        pressedDown = false

        player.MotionUp()
    }

    fun MotionEventDown() {
        pressedDown = true

        player.MotionDown()
    }


    fun gameOver() {
        //todo
    }

}
