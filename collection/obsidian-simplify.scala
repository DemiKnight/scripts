//> using scala 3.4.0
//> using dep com.lihaoyi::mainargs:0.7.0

import mainargs.{ParserForMethods, arg, main}

import scala.io.{BufferedSource, Source}
import scala.util.Using
import scala.util.matching.Regex

object ObsidianSimplify {

  val markdownHeader: Regex = """^((#{1,5} )(.*))$""".r

  @main
  def run(
      @arg(short = 'f', doc = "File to query") file: String,
      @arg(short = 'h', doc = "Markdown header to find and pull contents")
      header: Option[String]
  ): Unit = {
    Using(Source.fromFile(file)) { (file: BufferedSource) =>
      val test: Option[String] = file.getLines().find(s => markdownHeader.findFirstMatchIn(s).isDefined)
    }
  }

  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args)
}
