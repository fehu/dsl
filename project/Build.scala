import sbt._
import Keys._
import org.sbtidea.SbtIdeaPlugin._

object Build extends sbt.Build {

  val commonBuildSettings = Defaults.defaultSettings ++ Seq (
    organization  := "feh.dsl",
    crossScalaVersions := Seq("2.11.1", "2.10.3"),
      //    scalacOptions ++= Seq("-explaintypes"),
//    scalacOptions ++= Seq("-deprecation"),
    scalacOptions in (Compile, doc) ++= Seq("-diagrams")
  )

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  object Dependencies{
    def scalaSwing(version: String) = version match { // there is no "scala-swing" for 2.11.1 now, it's in lib-all
      case v if v startsWith "2.10" => "org.scala-lang" % "scala-swing" % v
      case v if v startsWith "2.11" => scalaLibAll(v)
    }
    def scalaLibAll(version: String) = "org.scala-lang" % "scala-library-all" % version // 2.11.x
    def reflectApi(version: String) = "org.scala-lang" % "scala-reflect" % version

    object feh{
      lazy val util = "feh" %% "util" % "1.0.2"
    }
  }

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  lazy val root = Project(
    id = "dsl-root",
    base = file("."),
    settings = commonBuildSettings
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