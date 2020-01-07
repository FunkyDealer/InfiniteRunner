package ipca.games.infiniterunner

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ipca.games.infiniterunner.EngineFiles.GameActivity
import ipca.games.infiniterunner.ui.Login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //Force LandScape Mode

        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}
