import sbt._

class SimpleHttpDispatchProject(info: ProjectInfo) extends ParentProject(info) with IdeaPlugin {

  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"



  lazy val incubator = project("incubator", "incubator", new IncubatorProject(_) with IdeaPlugin)

  class IncubatorProject(info: ProjectInfo) extends DefaultWebProject(info) {
    val servlet = "javax.servlet" % "servlet-api" % "2.5" withSources()
    
    val scalaTest = "org.scalatest" % "scalatest" % "1.0.1-for-scala-2.8.0.RC1-SNAPSHOT" % "test" withSources()

    val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.v20100331" % "test"

  }
}
