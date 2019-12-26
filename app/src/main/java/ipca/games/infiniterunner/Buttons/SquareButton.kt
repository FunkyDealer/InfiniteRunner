package ipca.games.infiniterunner.Buttons

import android.graphics.*
import ipca.games.infiniterunner.EngineFiles.GameTime
import ipca.games.infiniterunner.EngineFiles.Vector2

class SquareButton {


 var text: String
 var square: RectF


 constructor(text: String, start : Vector2, Width : Float, Height : Float) {

  this.text = text
  this.square = RectF(start.x, start.y, start.x + Width, start.y + Height)


 }

 fun update(gameTime: GameTime){



 }

 fun draw(canvas: Canvas, paint: Paint, gameTime: GameTime) {

  paint.color = Color.GREEN
  paint.textSize = 50f

  canvas.drawRect(square, paint)
  canvas.drawText(text, square.top, square.left, paint)


 }


}