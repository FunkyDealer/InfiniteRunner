package ipca.games.infiniterunner.EngineFiles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import ipca.games.infiniterunner.Game
import java.time.Duration
import java.time.Instant

class GameView: SurfaceView, Runnable {

    private var playing : Boolean = false
    private var gameThread : Thread? = null
    private var paint : Paint = Paint()
    private var canvas : Canvas = Canvas()
    private var surfaceHolder : SurfaceHolder = holder
    private var screenSize : Vector2



    private var game : Game
    var UpdategameTime : GameTime
    var DrawGameTime : GameTime
    var lastUpdate : Instant

    constructor(context: Context?, screenSize : Vector2) : super(context) {

        this.screenSize = screenSize
        UpdategameTime = GameTime()
        DrawGameTime = GameTime()
        lastUpdate = Instant.now()
        game = Game(context!!, screenSize)

    }


    override fun run() {
        while (playing) {
            var timeNow = Instant.now()

            UpdategameTime.update(Duration.between(lastUpdate, Instant.now()))
            update(UpdategameTime) //Update the frame



            DrawGameTime.update(Duration.between(lastUpdate, Instant.now()))
            draw(DrawGameTime) //Draw the Frame
            lastUpdate = timeNow

            control()

        }



    }

    fun update(gameTime: GameTime) {


        game.update(gameTime)


    }

    fun draw(gameTime: GameTime) {

        if (surfaceHolder.surface.isValid) {

            canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.BLACK)



            game.draw(canvas, paint, gameTime)



            surfaceHolder.unlockCanvasAndPost(canvas)

        }

    }

    fun control() {

        Thread.sleep(16)


    }

    fun pause() {
        playing = false
        gameThread!!.join()
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let{
            when(it.action.and(MotionEvent.ACTION_MASK)) {
                MotionEvent.ACTION_UP -> {
                    game.MotionEventUp()
                }
                MotionEvent.ACTION_DOWN -> {
                    game.MotionEventDown()
                }
            }
        }

        return true
    }





}

