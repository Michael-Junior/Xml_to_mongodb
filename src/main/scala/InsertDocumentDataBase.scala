

import scala.util.{Failure, Success, Try}

object InsertDocumentDataBase extends App {

  val importFileXmlLocal = new ImportFiles("/home/oliveirmic/local-documents")
  val connectionDataBase = new ConnectionDataBase("", "localhost", "27017", "DataProcessing", "fruits", true)



  def insertDocumentDatabase(matches: collection.mutable.ListBuffer[String]): Unit = {

    matches.foreach(f => {
      val document = importFileXmlLocal.xml2json(f)

      connectionDataBase.insertDocument(document) match
        case Failure(exception) => println(s"Error: $exception")
        case Success(id) => println(s"Success! id=$id")
    })
  }

  insertDocumentDatabase(importFileXmlLocal.Xml2String(importFileXmlLocal.listFiles))
}
