package ipca.games.infiniterunner.ui.Login

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ipca.games.infiniterunner.EngineFiles.GameActivity
import ipca.games.infiniterunner.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"
    lateinit var username : EditText
    lateinit var password : EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        username = findViewById<EditText>(R.id.userEditText)
        password = findViewById<EditText>(R.id.passEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)


        loginButton.setOnClickListener {
                login(
                    username!!.text.toString().trim(),
                    password!!.text.toString().trim()
                )
        }

        createNewButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, CreateLoginActivity::class.java)
            startActivity(intent)
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
        var usernameStored = prefs.getString("username", "")
        var passwordStored = prefs.getString("password", "")
        var rememberStored = prefs.getBoolean("remember", false)

        if (rememberStored) {
            username!!.setText(usernameStored)
            password!!.setText(passwordStored)
            rememberCheckBox.isChecked = true
        }

    }


    fun login(email : String, inPassword : String) {
        if (email != "" && inPassword != "") {
            auth.signInWithEmailAndPassword(email, inPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        if (rememberCheckBox.isChecked) {
                        val prefs = PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
                        prefs.edit().putString("username", username.text.toString())
                        prefs.edit().putString("password", password.text.toString())
                        prefs.edit().putBoolean("remember", true)
                        prefs.edit().commit()
                    } else {
                        val prefs = PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
                        prefs.edit().putString("username", username.text.toString())
                        prefs.edit().putString("password", password.text.toString())
                        prefs.edit().putBoolean("remember", false)
                        prefs.edit().commit()

                    }
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this@LoginActivity, GameActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    // ...
                }
        } else {
            Toast.makeText(
                baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



    //End class
}
