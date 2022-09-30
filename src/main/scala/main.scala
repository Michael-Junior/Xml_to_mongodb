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

  def main(listImports: Seq[String]): Unit = {

    listImports.foreach(f => {
      val document = importFileXmlLocal.xml2json(f)

      connectionMongo.insertDocument(document) match
        case Success(id) => println(s"Success! id=$id")
        case Failure(exception) => println(s"Error: $exception")

    })
  }

  main(importFileXmlLocal.file2String(importFileXmlLocal.listFiles))
}
