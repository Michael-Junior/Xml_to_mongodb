import org.json4s.JValue
import org.json4s.Xml.toJson
import org.json4s.native.JsonMethods.render
import org.json4s.native.Printer.compact

import java.io.{File, FilenameFilter}
import java.nio.file.{Files, Path, Paths}
import java.util
import scala.io.Source
import scala.xml.XML.loadString
import scala.xml.{Elem, SAXParseException, XML}

class ImportFiles(pathFolderImport: String, path: String = "/import", extensionFIle: String = ".xml") {

  val url: String = {pathFolderImport + path}
  val dirFile = new File(url)

  val listFiles = dirFile.listFiles(
    new FilenameFilter :
      override def accept(dir: File, name: String):
      Boolean = if name.endsWith(extensionFIle) then true
      else {
        LogGenerator(pathFolderImport).logGenerator(name)
        false
      }
  )

  def file2String(listFiles: Seq[File]) : Seq[String] = {

    listFiles.map(x => convertFileToJson(x))
//    listFiles.map(convertFileToJson(_))
//    listFiles.map(convertFileToJson)
  }

  def convertFileToJson(file: File) : String = {

    val xmlFile = File(file.toString)
    val byteFileXml: Array[Byte] = Files.readAllBytes(xmlFile.toPath)
    val xmlStr = new String(byteFileXml)

    xml2json(xmlStr)
  }

  def xml2json(xml: String): String = {

    try {
      val xmlElem: Elem = loadString(xml)
      val json: JValue = toJson(xmlElem)
      compact(render(json))

    } catch {
      case ex: SAXParseException => xml
    }
  }
}
