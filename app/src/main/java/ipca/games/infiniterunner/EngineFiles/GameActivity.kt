package ipca.games.infiniterunner.EngineFiles

import android.content.pm.ActivityInfo
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display

class GameActivity : AppCompatActivity() {

    var gameView : GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val display : Display = windowManager.defaultDisplay
        var size : Point = Point()
        display.getSize(size)

        gameView = GameView(this, Vector2(size))
        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView!!.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView!!.resume()
    }

}