package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object CanvasClock {

  @JSExport
  def time(canvas: html.Canvas): Unit = {
    val renderer = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientHeight

    renderer.textAlign = "center"

    val gradient = renderer.createLinearGradient(canvas.width / 2 - 100, 0, canvas.width / 2 + 100, 0)
    gradient.addColorStop(0, "red")
    gradient.addColorStop(0.5, "green")
    gradient.addColorStop(1, "blue")

    def renderTime() = {
      val date = new js.Date()
      renderer.font = "75px sans-serif"
      renderer.fillStyle = gradient
      renderer.clearRect(0, 0, canvas.width, canvas.height)
      renderer.fillText(Seq(date.getHours(), date.getMinutes(), date.getSeconds()).mkString(":"), canvas.width / 2, canvas.height / 2)
    }

    dom.setInterval(renderTime _, 1000)
  }

}
