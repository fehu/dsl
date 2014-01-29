import sbt._
import Keys._
import org.sbtidea.SbtIdeaPlugin._

object  Build extends sbt.Build {

  val ScalaVersion = "2.10.3"
  val Version = "1.0"

  import Dependencies._

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization  := "feh.dsl",
    scalaVersion  := ScalaVersion,
//    scalacOptions ++= Seq("-explaintypes"),
//    scalacOptions ++= Seq("-deprecation"),
    scalacOptions in (Compile, doc) ++= Seq("-diagrams", "-diagrams-debug")
  )

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  object Dependencies{
    lazy val scalaSwing = "org.scala-lang" % "scala-swing" % ScalaVersion
    lazy val reflectApi = "org.scala-lang" % "scala-reflect" % ScalaVersion

    object feh{
      lazy val util = "feh" %% "util" % "1.0.1"
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
    settings = buildSettings ++ Seq(
      version := "1.0",
      libraryDependencies ++= Seq(scalaSwing, feh.util)
    )
  )

  lazy val graphviz = Project(
    id = "graphviz",
    base = file("graphviz"),
    settings = buildSettings ++ Seq(
      version := "0.1",
      libraryDependencies += feh.util
    )
  )
}