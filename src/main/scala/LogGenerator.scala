import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class LogGenerator(pathFolderLog: String, path: String = "/log") {

  val messageLog: String = "Arquivo não importado para o banco de dados: "
  val dir: String = {pathFolderLog + path}

  def logGenerator(nameFileRejected: String, message: String = messageLog): Unit = {

    if (!Files.exists(Paths.get(dir))) Files.createDirectory(Paths.get(dir))
    createFileLog(s"$message $nameFileRejected.")
  }

  def createFileLog(message: String): Unit = {

    val pathFileLog = new File(s"$dir/logs.txt")
    if (!pathFileLog.exists) pathFileLog.createNewFile

    val fileWriter = new FileWriter(pathFileLog, true)
    val bufferedWriter = new BufferedWriter(fileWriter)

    bufferedWriter.write(message)
    bufferedWriter.newLine()
    bufferedWriter.close()
    fileWriter.close()
  }
}