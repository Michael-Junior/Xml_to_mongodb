import scala.util.{Failure, Success}

object main extends App {

  val importFileXmlLocal = new ImportFiles("/home/oliveirmic/local-documents")
  val connectionMongo = new ConnectionMongo(
    "",
    "localhost",
    "27017",
    "DataProcessing",
    "fruits",
    true
  )


  def main(listImports: collection.mutable.ListBuffer[String]): Unit = {

    listImports.foreach(f => {
      val document = importFileXmlLocal.xml2json(f)

      connectionMongo.insertDocument(document) match
        case Failure(exception) => println(s"Error: $exception")
        case Success(id) => println(s"Success! id=$id")
    })
  }

  main(importFileXmlLocal.Xml2String(importFileXmlLocal.listFiles))
}
