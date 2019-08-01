import akka.actor.ActorSystem
import akka.http.javadsl.model.headers.RawHeader
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{`User-Agent`,RawHeader}
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpHeader
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.headers.Cookie
import akka.util.ByteString

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}
import scala.concurrent.Future
object Client2 {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest( uri = "https://www.google.com/search?q=implicit&oq=impl&aqs=chrome.1.69i57j0l2j69i60j69i61l2.4856j0j9&sourceid=chrome&ie=UTF-8"))

    responseFuture
  .onComplete {
        case Success(res) => println(res)
        case Failure(_)   => sys.error("something wrong")}

  }
}
