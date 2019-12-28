package ipca.games.infiniterunner.ui.Login

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ipca.games.infiniterunner.EngineFiles.GameActivity
import ipca.games.infiniterunner.R

class CreateLoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var passwordConfirm : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContentView(R.layout.activity_create_login)

        auth = FirebaseAuth.getInstance()

        username = findViewById<EditText>(R.id.newEmailEditText)
        password = findViewById<EditText>(R.id.newPassEditText)
        passwordConfirm = findViewById<EditText>(R.id.newPassEditText2)
        val loginButton = findViewById<Button>(R.id.createUserButton)

        loginButton.setOnClickListener {
            CreateLogin(
                username!!.text.toString().trim(),
                password!!.text.toString().trim(),
                passwordConfirm!!.text.toString().trim()
            )
        }

    }

    fun CreateLogin(email : String, inPassword : String, inPasswordConfirm : String) {
if (email != "" && inPassword != "" && inPasswordConfirm != "") {
    if (inPassword == inPasswordConfirm) {
        auth.createUserWithEmailAndPassword(email, inPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this@CreateLoginActivity, GameActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Email not valid.", Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    } else {
        Toast.makeText(baseContext, "the 2 passwords are not the same", Toast.LENGTH_SHORT).show()
    }
} else {
    Toast.makeText(baseContext, "You must fill all fields!", Toast.LENGTH_SHORT).show()
}
    }



}
