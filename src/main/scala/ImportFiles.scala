import com.sun.source.tree.ContinueTree

import javax.xml.parsers.DocumentBuilderFactory

import java.io.{File, FilenameFilter}
import java.nio.file.{Files, Path, Paths}
import java.util

class ImportFiles(pathFolderImport: String, path: String = "/import", extensionFIle: String = ".xml") {

  val url: String = {pathFolderImport + path}
  val dirFile = new File(url)

  val listFiles: collection.mutable.Seq[File] = dirFile.listFiles(
    new FilenameFilter :
      override def accept(dir: File, name: String):
      Boolean = if name.endsWith(extensionFIle) then true
      else {
        LogGenerator(pathFolderImport).logGenerator(name)
        false
      }
  )

  def Xml2String(listFiles: collection.mutable.Seq[File]): collection.mutable.ListBuffer[String]  = {

    val listStrXml: collection.mutable.ListBuffer[String] = collection.mutable.ListBuffer[String]()

    listFiles.foreach(file => {
      val xmlFile = File(file.toString)
      val byteFileXml = Files.readAllBytes(xmlFile.toPath)
      val xmlStr = new String(byteFileXml)

      listStrXml += xmlStr
    })
    listStrXml
  }
}
