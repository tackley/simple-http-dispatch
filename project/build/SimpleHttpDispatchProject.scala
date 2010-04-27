import sbt._

class SimpleHttpDispatchProject(info: ProjectInfo) extends ParentProject(info) with IdeaPlugin {

  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  lazy val simpleDispatch = project("simple-dispatch", "simple-dispatch", new SimpleDispatchProject(_))
  lazy val exampleWebapp = project("example-webapp", "example-webapp", new ExampleWebAppProject(_) , simpleDispatch)

  class SimpleDispatchProject(info: ProjectInfo) extends DefaultProject(info) with IdeaPlugin {
    val servlet = "javax.servlet" % "servlet-api" % "2.5" withSources()
    val scalaTest = "org.scalatest" % "scalatest" % "1.0.1-for-scala-2.8.0.RC1-SNAPSHOT" % "test" withSources()
  }

  class ExampleWebAppProject(info: ProjectInfo) extends DefaultWebProject(info) with IdeaPlugin {
    val servlet = "javax.servlet" % "servlet-api" % "2.5" withSources()
    val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.v20100331" % "test"

 }

}
