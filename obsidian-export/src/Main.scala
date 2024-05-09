//> using scala 3.4.1
//> using dep com.lihaoyi::mainargs:0.7.0

import mainargs.{main, arg, ParserForMethods, Flag}

object Main{
  @main
  def run(@arg(short = 'f', doc = "String to print repeatedly")
          foo: String,
          @arg(doc = "How many times to print string")
          myNum: Int = 2,
          @arg(doc = "Example flag, can be passed without any value to become true")
          bool: Flag) = {
    println(foo * myNum + " " + bool.value)
  }
  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args)
}