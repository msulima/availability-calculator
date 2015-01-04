package pl.msulima.availability.calculator

import org.scalajs.dom

import scala.scalajs.js.annotation.JSExport

@JSExport
object App {

  @JSExport
  def main(container: dom.HTMLDivElement): Unit = {
    dom.console.log("main")

    container.removeChild(container.firstChild)
    container.appendChild(new CheatSheet().render)
  }
}
