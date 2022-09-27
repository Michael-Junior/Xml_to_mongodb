import scala.xml.XML.loadString
import scala.xml.{Elem, XML}
import org.json4s.native.Printer.compact
import org.json4s.native.JsonMethods.render
import org.json4s.Xml.toJson
import org.json4s.JValue

import java.nio.file.Paths
import scala.util.{Failure, Success, Try}

object InsertDocumentDataBase extends App {

  val importFileXmlLocal = new LocalDirectory()
  val connectionDataBase = new ConnectionDataBase("", "localhost", "27017", "DataProcessing", "fruits", true)

  def xml2json(xml: String): String = {

    val xmlElem: Elem = loadString(xml)
    val json: JValue = toJson(xmlElem)

    compact(render(json))
  }

  def insertDocumentDatabase(matches: collection.mutable.ListBuffer[String]): Unit = {

    matches.foreach(f => {
      val document = xml2json(f)

      connectionDataBase.insertDocument(document) match
        case Failure(exception) => println(s"Error: $exception")
        case Success(id) => println(s"Success! id=$id")
    })
  }
  insertDocumentDatabase(importFileXmlLocal.convertXmlString())
}
