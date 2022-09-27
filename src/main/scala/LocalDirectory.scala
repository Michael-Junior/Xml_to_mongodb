import com.sun.source.tree.ContinueTree

import javax.xml.parsers.DocumentBuilderFactory

import java.io.{File, FilenameFilter}
import java.nio.file.{Files, Path, Paths}
import java.util

class LocalDirectory() {

  val listDocumentosRecusados, listXmlImportados: collection.mutable.ListBuffer[String] = collection.mutable.ListBuffer[String]()

  val currentRelativePath: Path = Paths.get("/home/oliveirmic/local-documents")
  val url: String = currentRelativePath.toString + "/importTest/"
  val listFile = new File(url)

  val matches: collection.mutable.Seq[File] = listFile.listFiles(
    new FilenameFilter :
      override def accept(dir: File, name: String):
      Boolean = if name.startsWith("arquivo") && name.endsWith(".xml") then true
      else {
        listDocumentosRecusados += name
        LogGenerator().logGenerator(name)
        false
      }
  )

  def convertXmlString(): collection.mutable.ListBuffer[String] = {

    matches.foreach(f => {

      val xmlFile = new File(f.toString)
      val b = Files.readAllBytes(xmlFile.toPath)
      val xml = new String(b)

//      val dbf = DocumentBuilderFactory.newInstance
//      val db = dbf.newDocumentBuilder
//      val document = xmlFile.parse(db)

      listXmlImportados += xml
    })
    listXmlImportados
  }
}
