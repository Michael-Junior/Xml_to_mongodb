import java.io.File
import java.io.IOException
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException

import java.nio.file.{Path, Paths}
import scala.util.{Failure, Success}

object ValidateXML extends App {

  val currentRelativePath: Path = Paths.get("/home/oliveirmic/local-documents")
  val url: String = currentRelativePath.toString + "/importTest/"
  val listFile = new File(url)

  for (i <- listFile.listFiles()) {

    val file = new File(i.toString)

    val dbf = DocumentBuilderFactory.newInstance
    val db = dbf.newDocumentBuilder

    val document = db.parse(file)
    println(document)

    document.getDocumentElement.normalize()
    println("Root Element :" + document.getDocumentElement.getNodeName)
    val nList = document.getElementsByTagName("pessoa")

    println("----------------------------")

    for (temp <- 0 until nList.getLength) {
      val nNode = nList.item(temp)

      print("\nCurrent Element :" + nNode.getNodeName)

      if (nNode.getNodeType == Node.ELEMENT_NODE) {
        val eElement = nNode.asInstanceOf[Element]
        System.out.println("pessoa id : " + eElement.getAttribute("id"))
        System.out.println("nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent)
        System.out.println("idade : " + eElement.getElementsByTagName("idade").item(0).getTextContent)
      }
    }
  }
}
