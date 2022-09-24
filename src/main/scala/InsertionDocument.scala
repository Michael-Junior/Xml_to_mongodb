import scala.xml.XML.loadString
import scala.xml.{Elem, XML}
import org.json4s.native.Printer.compact
import org.json4s.native.JsonMethods.render
import org.json4s.Xml.toJson
import org.json4s.JValue

object InsertionDocument extends App {

  val connectionDataBase = new ConnectionDataBase(true)
  val importFileXmlLocal = new ConnectionLocal()

  def xml2json(xml: String): String = {

    val xmlElem: Elem = loadString(xml)
    val json: JValue = toJson(xmlElem)

    compact(render(json))
  }

  def insertDocumentDatabase(matches: collection.mutable.ListBuffer[String]): Unit = {

    matches.foreach(f => {
      val document = xml2json(f)
      connectionDataBase.insertDocument(document)
    })
  }

  insertDocumentDatabase(importFileXmlLocal.convertXmlString())
}
