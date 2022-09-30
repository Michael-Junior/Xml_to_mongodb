import requests.Response

object ImportApi extends App {

  val response: Response = requests.get("http://localhost:5000/posts")
  println(response.statusCode)

}


