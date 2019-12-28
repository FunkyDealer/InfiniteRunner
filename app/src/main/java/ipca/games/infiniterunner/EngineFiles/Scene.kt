package ipca.games.infiniterunner.EngineFiles

import android.graphics.Canvas
import android.graphics.Paint
import ipca.games.infiniterunner.Game

open class Scene {

    var updating : Boolean = false
    var drawing : Boolean = false
    var name : String
    protected var game : Game
    protected var screenSize : Vector2

    constructor(game : Game, screenSize : Vector2, name : String, playing : Boolean) {

        this.name = name
        this.game = game
        this.screenSize = screenSize
        SetActive(playing)


    }

    open fun Update(gameTime: GameTime) {



    }


    open fun Draw(canvas : Canvas, paint : Paint, gameTime: GameTime) {

    }


    public fun SetActive(active : Boolean)
    {
        updating  = active
        drawing = active
    }

    public fun SwitchActive() {
        if (updating && drawing) {
            SetActive(false)
        } else {
            SetActive(true)
        }
    }

    public fun SwitchUpdate() {
        if (updating) updating = false
        else updating = true
    }

    public fun SwitchDrawing() {
        if (drawing) drawing = false
        else drawing = true
    }




    open fun MotionEventUp() {

    }

    open fun MotionEventDown() {


    }


}