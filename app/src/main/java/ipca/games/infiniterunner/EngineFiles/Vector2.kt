package ipca.games.infiniterunner.EngineFiles

import android.graphics.Point
import java.util.*

class Vector2 {
    var x : Float
    var y : Float


    constructor(x: Float, y: Float) { //Make a Vector2 from 2 Floats
        this.x = x
        this.y = y
    }

    constructor(point : Point) { //Make a Vector2 From a Point
        this.x = point.x.toFloat()
        this.y = point.y.toFloat()
    }

    fun Length() : Float { //Return the Lenght of the Vector2
        var X = x.toDouble()
        var Y = y.toDouble()
        return Math.sqrt((X * X) + (Y * Y)).toFloat()
    }

    fun Normalize() : Vector2 { //Normalizes thes Vector2, making all values Equal
        var len = this.Length()
        if (len > 0) {
            return Vector2(x / len, y / len)
        } else return this
    }

    override fun toString() : String //COnverts A vector2 into a String
    {
        var string : String = "{X: $x; Y: $y}"
        return string
    }

    operator fun  plus(v2 : Vector2) : Vector2{ //Adds 2 Vectors, allows to use the + operator
        return Vector2(x + v2.x, y + v2.y)
    }

    operator fun minus(v2 : Vector2) : Vector2{ //Subtracts 2 Vectors, allows to use the - operator
        return Vector2(x - v2.x, y - v2.y)
    }

    operator fun times(v2 : Vector2) : Vector2{ //Multiplies 2 vectors, allows to use the * operator
        return Vector2(x * v2.x, y * v2.y)
    }

    operator fun  times(v2 : Float) : Vector2{ //multiplies a vector for a float, allows to use the * operator
        return Vector2(x * v2 , y * v2)
    }

    operator fun div(v2 : Vector2) : Vector2{ //Divides 2 vectors, allow to use the / operator
        return Vector2(x / v2.x, y / v2.y)
    }

    operator fun div(v2 : Float) : Vector2{ //Divides a vector for a float, allow to use the / operator
        return Vector2(x / v2, y / v2)
    }



    companion object {
        fun Up() : Vector2 {  return Vector2(0f, -1f) }
        fun Down() : Vector2 {  return Vector2(0f, 1f) }
        fun Left() : Vector2 {  return Vector2(-1f, 0f) }
        fun Right() : Vector2 {  return Vector2(1f, 0f) }
        fun Zero() : Vector2 {  return Vector2(0f, 0f) }
        fun One() : Vector2 {  return Vector2(1f, 1f) }




        fun add(a : Vector2, b : Vector2) : Vector2 { return Vector2(a.x + b.x, a.y + b.y)}
        fun subract(a : Vector2, b : Vector2) : Vector2 { return Vector2(a.x - b.x, a.y - b.y)}
        fun multiply(a : Vector2, b : Vector2) : Vector2 { return Vector2(a.x * b.x, a.y * b.y)}
        fun divide(a : Vector2, b : Vector2) : Vector2 { return Vector2(a.x / b.x, a.y / b.y)}


    }




}