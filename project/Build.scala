import sbt._
import Keys._
import org.sbtidea.SbtIdeaPlugin._

object  Build extends sbt.Build {

  val ScalaVersion = "2.10.3"
  val Version = "1.0"

  import Dependencies._

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization  := "feh",
    version       := Version,
    scalaVersion  := ScalaVersion,
//    scalacOptions ++= Seq("-explaintypes"),
//    scalacOptions ++= Seq("-deprecation"),
    scalacOptions in (Compile, doc) ++= Seq("-diagrams", "-diagrams-debug")
//     mainClass in Compile := Some("")
  )

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  object Dependencies{
    lazy val scalaSwing = "org.scala-lang" % "scala-swing" % ScalaVersion
//    lazy val reflectApi = "org.scala-lang" % "scala-reflect" % ScalaVersion

    object feh{
      lazy val util = "feh" %% "util" % "1.0"
    }
  }

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  lazy val swing = Project(
    id = "swing-dsl",
    base = file("."),
    settings = buildSettings ++ Seq(
      libraryDependencies ++= Seq(scalaSwing, feh.util)
    )
  ).settings(ideaExcludeFolders := ".idea" :: ".idea_modules" :: Nil)

}