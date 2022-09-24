import com.sun.source.tree.ContinueTree

import java.io.{File, FilenameFilter}
import java.nio.file.{Files, Path, Paths}
import java.util

class ConnectionLocal() {

  val currentRelativePath: Path = Paths.get("/home/oliveirmic/local-documents")
  val url: String = currentRelativePath.toString + "/Import"
  val dir = new File(url)

  val listXmlImportados = collection.mutable.ListBuffer[String]()
  val listDocumentosRecusados = collection.mutable.ListBuffer[String]()

  val matches: collection.mutable.Seq[File] = dir.listFiles(
    new FilenameFilter :
      override def accept(dir: File, name: String):
      Boolean = if name.startsWith("arquivo") && name.endsWith(".xml") then true
      else {
        listDocumentosRecusados += name
        GenerateLog().initLog(listDocumentosRecusados)
        false
      }
  )

  def convertXmlString(): collection.mutable.ListBuffer[String] = {

    matches.foreach(f => {
        println(s"Documentos inseridos com sucesso: $f")

        val xmlFile = new File(f.toString)
        val b = Files.readAllBytes(xmlFile.toPath)
        val xml = new String(b)
        listXmlImportados += xml
      })
    listXmlImportados
  }
}
