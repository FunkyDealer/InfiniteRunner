package ipca.games.infiniterunner.EngineFiles

import ipca.games.infiniterunner.Game

class Scene {

    var Updating : Boolean = false
    var Drawing : Boolean = false
    var name : String

    constructor(game : Game, name : String) {

        this.name = name
        SetActive(false)


    }

    open fun update() {

    }


    open fun draw() {

    }


    public fun SetActive(active : Boolean)
    {
        Updating  = active
        Drawing = active
    }



}