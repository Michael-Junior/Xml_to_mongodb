import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class LogGenerator {

  val messageLog: String = "Arquivo não inserido: "
  val pathFolderLog: Path = Paths.get("/home/oliveirmic/local-documents/log")

  def logGenerator(nameFileRejected: String, message: String = messageLog): Unit = {

    if (!Files.exists(pathFolderLog)) Files.createDirectory(pathFolderLog)
    createFileLog(s"$message $nameFileRejected.")
  }

  def createFileLog(message: String): Unit = {

    val pathFileLog = new File(s"$pathFolderLog/logs.txt")
    if (!pathFileLog.exists) pathFileLog.createNewFile

    val fileWriter = new FileWriter(pathFileLog, true)
    val bufferedWriter = new BufferedWriter(fileWriter)

    bufferedWriter.write(message)
    bufferedWriter.newLine()
    bufferedWriter.close()
    fileWriter.close()
  }
}