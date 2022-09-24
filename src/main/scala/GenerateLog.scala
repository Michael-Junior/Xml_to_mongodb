import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class GenerateLog {

  def initLog(listFiles: collection.mutable.ListBuffer[String]): Unit = {
    listFiles.foreach(f => {
      createFolderLog(s"Arquivo não inserido: $f")
    })  
  }

  def createFolderLog(message: String): Unit = {

    val path = Paths.get("/home/oliveirmic/local-documents/log")
    if (!Files.exists(path)) Files.createDirectory(path)
    createDescribesLog(message)
  }
  def createDescribesLog(message: String): Unit = {

    val log = new File("/home/oliveirmic/local-documents/log/logs.txt")

    if (!log.exists) log.createNewFile

    val fileWriter = new FileWriter(log, true)
    val bufferedWriter = new BufferedWriter(fileWriter)

    bufferedWriter.write(message)
    bufferedWriter.newLine()
    bufferedWriter.close()
    fileWriter.close()
  }
}