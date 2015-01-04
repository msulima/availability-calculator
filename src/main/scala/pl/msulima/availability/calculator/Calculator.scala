package pl.msulima.availability.calculator

object Calculator {

  def calculateAvailability(n: Int, k: Int, p: Double): Double = {
    (k to n).map(i => bernoulli(n, i, p)).sum
  }

  private def bernoulli(n: Int, k: Int, p: Double) = {
    val q = 1 - p

    binomialCoefficient(n, k) * Math.pow(p, k) * Math.pow(q, n - k)
  }

  private def binomialCoefficient(n: Int, k: Int) = {
    (1 to k).map(i => n - i + 1).product / (1 to k).product
  }
}
