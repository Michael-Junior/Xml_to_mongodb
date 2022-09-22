import com.sun.source.tree.ContinueTree

import java.io.{File, FilenameFilter}
import java.nio.file.{Files, Path, Paths}
import java.util

class ConnectionLocal() {

  val currentRelativePath: Path = Paths.get("/home/oem/Meus_projetos/")
  val url: String = currentRelativePath.toString + "/Import"
  val dir = new File(url)

  val listXmlImportados = new util.ArrayList[String]
  val listDocumentosRecusados = new util.ArrayList[String]

  val matches: Array[File] = dir.listFiles(
    new FilenameFilter :
      override def accept(dir: File, name: String):
      Boolean = if name.startsWith("arquivo") && name.endsWith(".xml") then true
      else {
        listDocumentosRecusados.add(name)
        GenerateLog().initLog(listDocumentosRecusados)
        false
      }
  )

  def convertXmlString(): util.ArrayList[String] = {

    matches.foreach(f => {
        println(s"Documentos inseridos com sucesso: $f")

        val xmlFile = new File(f.toString)
        val b = Files.readAllBytes(xmlFile.toPath)
        val xml = new String(b)
        listXmlImportados.add(xml)
      })
    listXmlImportados
  }
}
