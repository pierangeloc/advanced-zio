val ZIOVersion = "2.0.0-M4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "advanced-zio",
    organization := "net.degoes",
    scalaVersion := "2.12.11",
    initialCommands in Compile in console :=
      """|import zio._
         |import zio.Console._
         |implicit class RunSyntax[E, A](io: ZIO[ZEnv, E, A]){ def unsafeRun: A = Runtime.default.unsafeRun(io) }
    """.stripMargin,
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "check",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

libraryDependencies ++= Seq(
  // ZIO
  "dev.zio" %% "zio"          % ZIOVersion,
  "dev.zio" %% "zio-streams"  % ZIOVersion,
  "dev.zio" %% "zio-test"     % ZIOVersion,
  "org.typelevel" %% "cats-effect" % "3.2.9",
  "dev.zio" %% "zio-test"     % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test",
  "io.github.dimitarg"  %%  "weaver-test-extra" % "0.4.6" % "test",
  // URL parsing
  "io.lemonlabs" %% "scala-uri" % "1.4.1"
)

testFrameworks := Seq(
  new TestFramework("zio.test.sbt.ZTestFramework"),
  new TestFramework("weaver.framework.TestFramework")
)

scalacOptions in Compile in console := Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:existentials",
  "-Yno-adapted-args",
  "-Xsource:2.13",
  "-Yrepl-class-based",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-Xfuture",
  "-unchecked",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-opt-warnings",
  "-Ywarn-extra-implicit",
  "-Ywarn-unused:_,imports",
  "-Ywarn-unused:imports",
  "-opt:l:inline",
  "-opt-inline-from:<source>",
  "-Ypartial-unification",
  "-Yno-adapted-args",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit"
)
