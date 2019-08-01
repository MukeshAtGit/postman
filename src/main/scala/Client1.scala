import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpHeader, _}
import akka.http.scaladsl.model.headers.{Cookie, HttpCookiePair, `Set-Cookie`}
import akka.http.scaladsl.model.headers.RawHeader
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}

object Client1 {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    //    def cookie: HttpHeader = RawHeader("Cookie", "JSESSIONID=773443A914F739FD46E813F0B303EE89; vid=rBEAAl04J3mYswBJRdc5Ag==; _ga=GA1.2.536294751.1563961212; D_ZID=341F7885-7CE9-3257-B6C5-C710A1883A09; D_ZUID=73050FFD-B0E9-3C5F-9CF5-28D2DCBEFA27; D_HID=15EFE808-3D1A-3B23-9F7D-38071BAAF7CC; D_SID=103.245.119.90:XA0fKC+Ysv7sUHf6bEUXeqcr1o+N+Pv2mt2t7Kn1k0w; __qca=P0-937342400-1563961213090; d7s_uid=jyh24sd4ubd1zs; ntv_fpc=f7ce4a00-0d63-4552-bbf6-175bc12f57a8; __gads=ID=fa933cb3dd5a5404:T=1563961227:S=ALNI_MbbqHc4EDsXrIGLda1QDVnJe-tgJw; __utmz=182075681.1563966168.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); landc=errorPage; NC_VARNISH=true; fts=sNkSEdxpNsq0jvM_UIaWkA; amplitude_idowler.com=eyJkZXZpY2VJZCI6IjJkZWY2YWQ0LTIyNzEtNGE2Yi05NDVjLWY3YTY0ZWM2YzJiMlIiLCJ1c2VySWQiOiI1OTEyNTI5Iiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTYzOTY4OTkxNzA4LCJsYXN0RXZlbnRUaW1lIjoxNTYzOTY4OTkxNzM4LCJldmVudElkIjoxLCJpZGVudGlmeUlkIjowLCJzZXF1ZW5jZU51bWJlciI6MX0=; __utma=182075681.536294751.1563961212.1563966168.1563968992.2; _gid=GA1.2.1006588345.1564387682; OWLER_WEBID=5d3eabab6e0f26497d71177d; cpviewed=9231654%2C4396732%2C4396732%2C4396732; OWLER_PC=I5g35kM_neSz5l4X_4ofYXv92j0Nx7HiX1bn7eXcpjtBwEvH4dmGDLLrcN5JFuH1-I6SSlWXLHmYJWRU-QibxIJ1-MGM702IHhslq0kNhfitUeLrow_ZrSAAS4s6lwuzjvsx7xSn0dqxR8MLIUzcgw; __rtgt_sid=jyoa55ohy6ca3d; amplitude_id_e102edba5e9caea6b89e3c04fac87a4dowler.com=eyJkZXZpY2VJZCI6IjcxOGE2NTllLWU5ZTktNDUwMC1iOTgzLWJkNjI1OWI0ZjlhNFIiLCJ1c2VySWQiOiI1OTEyNTI5Iiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTY0Mzk3OTQyMTc4LCJsYXN0RXZlbnRUaW1lIjoxNTY0Mzk4NTk3NTE5LCJldmVudElkIjo1OSwiaWRlbnRpZnlJZCI6MjEsInNlcXVlbmNlTnVtYmVyIjo4MH0=; _gat=1; d7s_spc=2; D_IID=B8A56EF1-41AA-354E-9043-89BE62F02167; D_UID=AFAAB6D4-AA5A-3B85-8E85-46E391C3C8AE; li_c=1")
    //
    //    def userAgent: HttpHeader = RawHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36")

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "https://www.google.com/search?q=implicit&oq=impl&aqs=chrome.1.69i57j0l2j69i60j69i61l2.4856j0j9&sourceid=chrome&ie=UTF-8"))

    responseFuture.onComplete(response => {
      val cookies = response.get.headers.collect {
        case c: `Set-Cookie` => c.cookie
      }
      println(cookies)
    }
    )
  }
}

