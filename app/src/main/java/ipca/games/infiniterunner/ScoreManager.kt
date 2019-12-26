package ipca.games.infiniterunner

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.dateToString
import java.util.*
import kotlin.collections.ArrayList

class ScoreManager {


    var scoreList : MutableList<Score> = ArrayList<Score>()
    private val game : Game
    private var currentScore : Score
    private var scoreTimer : Float
    private val context : Context?
    //val userHighestScore : Score

    constructor(game: Game, context: Context?) {
        this.game = game
        currentScore = Score(0, Date())
        scoreTimer = 0f
        this.context = context
    }

    fun update(gameTime: GameTime, level : Int) {

        scoreTimer += gameTime.Delta()
        if (scoreTimer > 0.5f) {
            currentScore.score += 1 + level
            scoreTimer = 0f
        }



    }



    fun getScoreList() { //Gets scoreManager list from online




    }

    fun getHighestScore() {



    }

    fun saveScoreToFireBase() {

        var uploadScore = currentScore
        val database = FirebaseDatabase.getInstance() //.reference

        val myRef = database.getReference("Players")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .child("Score")


        myRef.push().setValue(uploadScore)

        //database.child("players").setValue(uploadScore) //.child(FirebaseAuth.getInstance().currentUser!!.uid)


    }

    fun updateScoreList() { //Updates scoreManager list with new Score

        if (scoreList.size < 1) {
            scoreList.add(currentScore)
        }

    }

    override fun toString() : String {
        return "${currentScore.score}"
    }

    public fun getCurrentScore() : Int { //Returns current Score
       return currentScore.score
    }

    public fun addCurrentScore(value : Int) { //adds to the current score
        currentScore.score +=  value
    }

    public fun subtractCurrentScore(value : Int) { //Substract from the current Score
        currentScore.score -=  value
    }
}