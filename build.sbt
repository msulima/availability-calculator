import com.lihaoyi.workbench.Plugin._

import scala.scalajs.sbtplugin.ScalaJSPlugin._

scalaJSSettings

workbenchSettings

name := "Availability calculator"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
  "com.scalatags" %%% "scalatags" % "0.4.0",
  "com.scalarx" %%% "scalarx" % "0.2.6"
)

bootSnippet := "App().main(document.getElementById('container'));"

updateBrowsers <<= updateBrowsers.triggeredBy(ScalaJSKeys.fastOptJS in Compile)
