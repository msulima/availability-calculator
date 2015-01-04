package pl.msulima.availability.calculator

import org.scalajs.dom
import pl.msulima.availability.calculator.JsDom.all._
import pl.msulima.availability.calculator.RxTags.rxFrag
import rx._

import scala.util.Try

class CheatSheet {

  private val AvailabilityFieldId = "avl"
  private val SizeFieldId = "size"

  private val availability = Var(99.0)
  private val size = Var(10)

  private val availabilityInput = input(id := AvailabilityFieldId, tpe := "text", cls := "form-control", value := f"${availability()}%.1f")().render
  private val sizeInput = input(id := SizeFieldId, tpe := "text", cls := "form-control", value := size(), "maxLength".attr := 2)().render

  availabilityInput.onkeyup = (e: dom.Event) => {
    Try(availabilityInput.value.toFloat).filter(x => x >= 0 && x <= 100).map(availability() = _)
  }
  sizeInput.onkeyup = (e: dom.Event) => {
    Try(sizeInput.value.toInt).filter(x => x > 0 && x <= 15).map(size() = _)
  }

  val render = div()(
    h1("Cheat sheet"),
    form(cls := "form-horizontal")(
      fieldLabel(AvailabilityFieldId, "Availability"),
      div(cls := "col-sm-1")(
        div(cls := "input-group")(
          availabilityInput,
          div(cls := "input-group-addon")("%")
        )
      ),
      fieldLabel(SizeFieldId, "Max nodes"),
      div(cls := "col-sm-1")(
        sizeInput
      )
    ),
    cheatSheet()
  ).render

  private def fieldLabel(id: String, labelText: String) = {
    label(`for` := id, cls := "col-sm-1 control-label")(labelText)
  }

  private def cheatSheet() = Rx {
    table(cls := "table table-striped")(
      cheatSheetHead(),
      cheatSheetBody()
    )
  }

  private def cheatSheetHead() = {
    thead()(
      tr()(
        th()("#"),
        (1 to size()).map(i => th()(i))
      )
    )
  }

  private def cheatSheetBody() = {
    tbody()(
      (1 to size()).map(i => {
        tr()(
          th("scope".attr := "row")(i),
          (1 to i).map(j => {
            td()(availabilityCell(i, j))
          }),
          fillMissing(size() - i)
        )
      })
    )
  }

  private def availabilityCell(i: Int, j: Int) = {
    val a = Calculator.calculateAvailability(i, j, availability() / 100)
    val prettyPrint = Printer.prettyPrint(a)

    span(title := a)(
      prettyPrint
    )
  }

  private def fillMissing(count: Int) = {
    Seq.fill(count)(
      td()("-")
    )
  }
}
