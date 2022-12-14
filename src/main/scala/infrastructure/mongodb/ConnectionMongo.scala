import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.{MongoClient, MongoClients, MongoCollection, MongoDatabase}
import org.bson.{BsonValue, Document}

import java.io.FileNotFoundException
import java.util
import scala.jdk.CollectionConverters.{CollectionHasAsScala, SeqHasAsJava}
import scala.util.Try

class ConnectionMongo(usrPswStr: String, hostStr: String, portStr: String, database: String, collection: String, clear: Boolean) {
  
  val mongoUri: String = s"mongodb://$usrPswStr$hostStr:$portStr"
  val mongoClient: MongoClient = MongoClients.create(mongoUri)
  val dbase: MongoDatabase = mongoClient.getDatabase(database)
  val this.clear: Boolean = clear
  val coll: MongoCollection[Document] = if clear then
    dbase.getCollection(collection).drop()
    dbase.getCollection(collection)
  else
    dbase.getCollection(collection)

  def insertDocument(doc: String): Try[String] = {

    Try{
      val document: Document = Document.parse(doc)
      val insertResult: InsertOneResult = coll.insertOne(document)

      insertResult. getInsertedId.toString
    }
  }

  def insertDocuments(docs: Seq[String]): Try[Seq[String]] = {
    Try{
      val documents: util.List[Document] = docs.map(Document.parse).asJava
      val insertedIds: util.Collection[BsonValue] = coll.insertMany(documents).getInsertedIds.values()

      insertedIds.asScala.toSeq.map(_.asObjectId().getValue.toString())
    }
  }
}
