package ipca.games.infiniterunner

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ipca.games.infiniterunner.EngineFiles.GameActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val intent = Intent(this@MainActivity, GameActivity::class.java)
        startActivity(intent)
    }
}
