//> using scala 3.3.3
//> using toolkit default
import os.CommandResult

val ignorePrefix = Seq(
    "-","_","."
)

def ignorePath(path: os.Path): Boolean = {
    path.baseName.trim.isEmpty || ignorePrefix.exists(path.baseName.startsWith(_)) || path.toIO.isFile
}
def successLine(str: String): Unit = {
    println(s"Success: ${str.trim()}")
}
def failureLines(code: Int, lines: Seq[String]): Unit = {
    println(s"Failure, $code: ${lines.mkString("\n")}}")
}

@main
def main(): Unit = {
    val foldersToSetup = os.list(os.pwd).filterNot(ignorePath)

    val result = foldersToSetup.map(p => os.proc("scala-cli", "setup-ide", p).call(cwd = os.pwd))

    val outputResults = result.map {
        case CommandResult(_, 0, Seq(Left(str))) =>  successLine(str.toString.trim())
        case CommandResult(_, errorCode, outputs) => failureLines(errorCode, outputs.map(_.merge.toString))
    }
}