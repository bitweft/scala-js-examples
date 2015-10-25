package example

import org.scalajs.dom
import org.scalajs.dom.{MouseEvent, html}

import scala.scalajs.js.annotation.JSExport

@JSExport
object Sketchpad {

  @JSExport
  def sketch(canvas: html.Canvas): Unit = {
    val renderer = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    var down = false

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientHeight

    renderer.fillStyle = "#e8eae8"
    renderer.fillRect(0, 0, canvas.width , canvas.height)

    canvas.onmousedown = { (m: MouseEvent) => down = true}
    canvas.onmouseup = { (m: MouseEvent) => down = false}
    canvas.onmousemove = { (e: MouseEvent) =>
      val rect = canvas.getBoundingClientRect()
      if (down) {
        renderer.fillStyle = "#000000"
        renderer.fillRect(e.clientX - rect.left, e.clientY - rect.top, 10, 10)
      }
    }
  }
}
