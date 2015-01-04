package pl.msulima.availability.calculator

object Printer {

  private val Minute = 60
  private val Hour = 60 * Minute
  private val Day = 24 * Hour
  private val Year = 365 * Day
  private val MaxSystemAge = 20

  def prettyPrint(availability: Double) = {
    val downtimeInYear = (1 - availability) * Year

    if (downtimeInYear < (1 / MaxSystemAge.toDouble)) {
      "Never"
    } else if (downtimeInYear < 1) {
      f"1 second per ${1 / downtimeInYear}%.1f years"
    } else {
      val days = Math.floor(downtimeInYear / Day)
      val hours = Math.floor(downtimeInYear % Day / Hour)
      val minutes = Math.floor(downtimeInYear % Hour / Minute)
      val seconds = Math.floor(downtimeInYear % Minute)

      f"$days days $hours:$minutes%02.0f:$seconds%02.0f"
    }
  }
}
