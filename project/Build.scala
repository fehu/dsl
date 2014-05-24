import sbt._
import Keys._
import org.sbtidea.SbtIdeaPlugin._

object Build extends sbt.Build {

  val commonBuildSettings = Defaults.defaultSettings ++ Seq (
    organization  := "feh.dsl",
    crossScalaVersions := Seq("2.10.3", "2.11.1"),
      //    scalacOptions ++= Seq("-explaintypes"),
//    scalacOptions ++= Seq("-deprecation"),
    scalacOptions in (Compile, doc) ++= Seq("-diagrams")
  )

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  object Dependencies{
    def scalaSwing(version: String) = "org.scala-lang" % "scala-swing" % version
    def reflectApi(version: String) = "org.scala-lang" % "scala-reflect" % version

    object feh{
      lazy val util = "feh" %% "util" % "1.0.2"
    }
  }

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  lazy val root = Project(
    id = "dsl-root",
    base = file(".")
  ).settings(ideaExcludeFolders := ".idea" :: ".idea_modules" :: Nil)
   .aggregate(swing, graphviz)

  lazy val swing = Project(
    id = "swing",
    base = file("swing"),
    settings = commonBuildSettings
  )

  lazy val graphviz = Project(
    id = "graphviz",
    base = file("graphviz"),
    settings = commonBuildSettings
  )
}