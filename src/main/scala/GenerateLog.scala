import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util

class GenerateLog {

  def initLog(listFiles: util.ArrayList[String]): Unit = {
    listFiles.forEach(f => {
      createFolderLog(s"Arquivo não inserido: $f")
    })
  }

  def createFolderLog(message: String): Unit = {

    val path = Paths.get("/home/oem/Meus_projetos/log")
    if (!Files.exists(path)) Files.createDirectory(path)
    createDescribesLog(message)
  }


  def createDescribesLog(message: String) : Unit = {

    val log = new File("/home/oem/Meus_projetos/log/logs.txt")

    if (!log.exists) log.createNewFile

    val fileWriter = new FileWriter(log, true)
    val bufferedWriter = new BufferedWriter(fileWriter)

    bufferedWriter.write(message)
    bufferedWriter.newLine()
    bufferedWriter.close()
    fileWriter.close()
  }
}