package ipca.games.infiniterunner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import ipca.games.infiniterunner.Collectables.CollectablesManager
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Scene
import ipca.games.infiniterunner.EngineFiles.Vector2
import ipca.games.infiniterunner.GameObjects.FloorManager
import ipca.games.infiniterunner.GameObjects.Player
import ipca.games.infiniterunner.GameObjects.ThirstBar

class RunnerGame : Scene {

    var player : Player
    var floorManager : FloorManager

    var gravity : Vector2 = Vector2(0f, 5f)


    var thirstBar : ThirstBar
    var floorSize : Vector2
    private var collectables : CollectablesManager
    private var level : Int
    private var levelTimer : Float




    constructor(context: Context?, screenSize : Vector2, game : Game, name : String, playing : Boolean) : super(game, screenSize, name, playing) {
        player = Player(
            context!!,
            this,
            Vector2(screenSize.x / 6, 300f),
            screenSize
        )
        floorManager = FloorManager(context, screenSize, this)
        thirstBar = ThirstBar(
            context,
            this,
            Vector2(0f, 0f),
            3,
            screenSize
        )
        floorSize = floorManager.floorList[0].frameSize
        collectables = CollectablesManager(context, screenSize, this)
        level = 0
        levelTimer = 0f


    }

    override fun Update(gameTime: GameTime) {
            levelTimer += gameTime.Delta()
            if (levelTimer > 30) {
                level++
                levelTimer = 0f
            }

            game.scoreManager.update(gameTime, level)





            player.update(gameTime)
            floorManager.update(gameTime, player.speed * (1 + (level * 0.1f)))
            thirstBar.update(gameTime)
            collectables.update(gameTime, player.speed * (1 + (level * 0.1f)))

    }

    override fun Draw(canvas : Canvas, paint : Paint, gameTime: GameTime) {
        canvas.drawColor(Color.BLACK)

        player.draw(canvas, paint)
        floorManager.draw(canvas, paint)
        thirstBar.draw(canvas, paint)
        collectables.draw(canvas, paint)

        paint.color = Color.GREEN
                paint.textSize = 50f

        canvas.drawText("Level: " + (level + 1), screenSize.x - 300, screenSize.y - 50, paint)
        canvas.drawText("Score: " + game.scoreManager, screenSize.x - 300, screenSize.y - 100, paint)



    }

    override fun MotionEventUp() {

        player.MotionUp()
    }

    override fun MotionEventDown() {

        player.MotionDown()
    }


    fun gameOver() {
        game.scoreManager.saveScoreToFireBase()
        SetActive(false)
        game.gameOverScreen.SetActive(true)
    }

    fun ScoreMan() : ScoreManager {
        return game.scoreManager
    }

    fun reboot(scene: Scene) {
        scene.SwitchActive()
        game.scoreManager.reboot()
        collectables.reboot()
        player.reboot()
        thirstBar.reboot()


        level = 0
        levelTimer = 0f

       this.SwitchActive()

    }

}