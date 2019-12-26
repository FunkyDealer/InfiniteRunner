package ipca.games.infiniterunner

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import ipca.games.infiniterunner.Buttons.SquareButton
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Scene
import ipca.games.infiniterunner.EngineFiles.Vector2

class MainMenu : Scene {

    var StartGameButton : SquareButton

    constructor(game: Game, screenSize: Vector2, name: String, playing: Boolean) : super(game,screenSize,name,playing) {

        StartGameButton = SquareButton("Start Game", Vector2(screenSize.x/ 2, screenSize.y / 2), 50f, 50f)
    }

    override fun Update(gameTime: GameTime) {
        super.Update(gameTime)



    }

    override fun Draw(canvas: Canvas, paint: Paint, gameTime: GameTime) {
        super.Draw(canvas, paint, gameTime)

        paint.color = Color.GREEN
        paint.textSize = 50f

        canvas.drawText("GAME TITLE", screenSize.x / 2, screenSize.y / 2 - 100, paint)
        StartGameButton.draw(canvas, paint, gameTime)

    }


    override fun MotionEventUp() {
        super.MotionEventUp()


    }

    override fun MotionEventDown() {
        super.MotionEventDown()


    }


}