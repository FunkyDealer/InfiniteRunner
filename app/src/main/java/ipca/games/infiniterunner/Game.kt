package ipca.games.infiniterunner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceView
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2

class Game : SurfaceView {


    var screenSize : Vector2

    var fps : Long = 0

    var pressedDown : Boolean = false


    var runnerGame : RunnerGame
    var gameOverScreen : GameOverScreen
    var scoreManager : ScoreManager
    var mainMenu : MainMenu
    var online : Boolean


    constructor(context: Context?, screenSize : Vector2) : super(context) {

        this.screenSize = screenSize

        online = true

        scoreManager = ScoreManager(this, context)

        runnerGame = RunnerGame(context, screenSize, this, "mainGame", true)
        gameOverScreen = GameOverScreen(this, screenSize, "GameOver", false)
        mainMenu = MainMenu(this, screenSize, "MainMenu", false)

    }

    /*
     * This is the Update Function, where objects are updated
     */
    fun Update(gameTime: GameTime) {

        when (gameTime.ElapsedGameTime.toMillis() > 0) {
            (true) -> fps = 1000 / gameTime.ElapsedGameTime.toMillis()
            (false) -> fps = 0
        }

        if (runnerGame.updating) runnerGame.Update(gameTime)
        else if (gameOverScreen.updating) gameOverScreen.Update(gameTime)
        else if (mainMenu.updating) mainMenu.Update(gameTime)

    }


    /*
    * This is the Draw Function, where Objects are Drawn on screen
    */
    fun Draw(canvas : Canvas, paint : Paint, gameTime: GameTime) {
        canvas.drawColor(Color.BLACK)

        if (runnerGame.drawing) runnerGame.Draw(canvas, paint, gameTime)
        else if (gameOverScreen.drawing) gameOverScreen.Draw(canvas, paint, gameTime)
        else if (mainMenu.drawing) mainMenu.Draw(canvas, paint, gameTime)

        paint.color = Color.GREEN
        paint.textSize = 50f

        canvas.drawText("fps: " + fps, 0f, 60.0f, paint)
        canvas.drawText("gametime: " + gameTime.Delta().toString(), screenSize.x / 2f, 310.0f, paint)
        canvas.drawText("pressing Down: " + pressedDown, screenSize.x / 2f, 360.0f, paint)
    }

/*
* Place here Motion Events
*/
    fun MotionEventUp() {
    pressedDown = false

    if (runnerGame.updating) runnerGame.MotionEventUp()

    }

    fun MotionEventDown() {
        pressedDown = true

        if (runnerGame.updating) runnerGame.MotionEventDown()

    }




}
