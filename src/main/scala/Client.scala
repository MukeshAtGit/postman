import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString
import akka.http.scaladsl.model.headers. RawHeader

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Client {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
//    def cookieHeader: HttpHeader = RawHeader("Cookie", "JSESSIONID=C7B94E92B81A879A92DA138E351DEC6B; vid=rBEAAl04MiY6WgBISUOcAg==; D_IID=09D61804-4E2C-376D-B3B2-232C5B963C4A; D_UID=C50AF571-7D60-3382-9F41-8B0DA10B30A9; D_ZID=AC8964EF-236C-35C0-9C62-25805BDF05A2; D_ZUID=5D5E1DF8-6984-3034-AA2D-2878F0BD7473; D_HID=9FFFD8D3-95E1-3128-9C02-6844518C9154; D_SID=103.62.237.65:iofim6Lw/nbEvV1n3FoMPWFpcOSKvqSTG5HuC3pQ1do; _ga=GA1.2.1018737922.1563963946; _gid=GA1.2.88332800.1563963946; amplitude_id_e102edba5e9caea6b89e3c04fac87a4dowler.com=eyJkZXZpY2VJZCI6IjQyNzFlNTIxLWVkNDktNDViYy1hZjY5LTg2NTlkYzgxZjNiMFIiLCJ1c2VySWQiOiI1OTEyNTYyIiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTYzOTY5MzY1Mjc5LCJsYXN0RXZlbnRUaW1lIjoxNTYzOTY5NjQ1MjIyLCJldmVudElkIjoyMiwiaWRlbnRpZnlJZCI6MTAsInNlcXVlbmNlTnVtYmVyIjozMn0=; li_c=1; __qca=P0-1428244705-1563963947904; d7s_uid=jyh3rc7ao1y4oc; __gads=ID=81f3f3ac89fb3840:T=1563963963:S=ALNI_Mb66lskUADreULNiiZBeiMv9LoZCg; onBoardingEmail=anshika.agrawal@knoldus.in; NC_VARNISH=true; fts=sNkSEdxpNsq0jvM_UIaWkA; ntv_fpc=4f4404ff-400b-42c3-b43f-4cc19a048b5c; OWLER_WEBID=5d383a596e0f26497d6be3f9; OWLER_PC=66-P_tC5n76BXNm4jTcvkCHQVD-D8Ka_adfyqOOS9Se2ZNdwROXjVVav6n-xocpaxGKiGTZwrDRDIDiXW1cnh1i0SJcagcQcc7HWHZQkOietUeLrow_ZrSAAS4s6lwuzeuF79OLQ2oKbEYS1EO9eZw; __rtgt_sid=jyh6z9v0xynlgq; d7s_spc=2")
    def cookieHeader: HttpHeader = RawHeader("Cookie", "JSESSIONID=773443A914F739FD46E813F0B303EE89; vid=rBEAAl04J3mYswBJRdc5Ag==; _ga=GA1.2.536294751.1563961212; D_ZID=341F7885-7CE9-3257-B6C5-C710A1883A09; D_ZUID=73050FFD-B0E9-3C5F-9CF5-28D2DCBEFA27; D_HID=15EFE808-3D1A-3B23-9F7D-38071BAAF7CC; D_SID=103.245.119.90:XA0fKC+Ysv7sUHf6bEUXeqcr1o+N+Pv2mt2t7Kn1k0w; __qca=P0-937342400-1563961213090; d7s_uid=jyh24sd4ubd1zs; ntv_fpc=f7ce4a00-0d63-4552-bbf6-175bc12f57a8; __gads=ID=fa933cb3dd5a5404:T=1563961227:S=ALNI_MbbqHc4EDsXrIGLda1QDVnJe-tgJw; __utmz=182075681.1563966168.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); landc=errorPage; NC_VARNISH=true; fts=sNkSEdxpNsq0jvM_UIaWkA; amplitude_idowler.com=eyJkZXZpY2VJZCI6IjJkZWY2YWQ0LTIyNzEtNGE2Yi05NDVjLWY3YTY0ZWM2YzJiMlIiLCJ1c2VySWQiOiI1OTEyNTI5Iiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTYzOTY4OTkxNzA4LCJsYXN0RXZlbnRUaW1lIjoxNTYzOTY4OTkxNzM4LCJldmVudElkIjoxLCJpZGVudGlmeUlkIjowLCJzZXF1ZW5jZU51bWJlciI6MX0=; __utma=182075681.536294751.1563961212.1563966168.1563968992.2; _gid=GA1.2.1006588345.1564387682; OWLER_WEBID=5d3eabab6e0f26497d71177d; cpviewed=9231654%2C4396732%2C4396732%2C4396732; OWLER_PC=I5g35kM_neSz5l4X_4ofYXv92j0Nx7HiX1bn7eXcpjtBwEvH4dmGDLLrcN5JFuH1-I6SSlWXLHmYJWRU-QibxIJ1-MGM702IHhslq0kNhfitUeLrow_ZrSAAS4s6lwuzjvsx7xSn0dqxR8MLIUzcgw; __rtgt_sid=jyoa55ohy6ca3d; amplitude_id_e102edba5e9caea6b89e3c04fac87a4dowler.com=eyJkZXZpY2VJZCI6IjcxOGE2NTllLWU5ZTktNDUwMC1iOTgzLWJkNjI1OWI0ZjlhNFIiLCJ1c2VySWQiOiI1OTEyNTI5Iiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTY0Mzk3OTQyMTc4LCJsYXN0RXZlbnRUaW1lIjoxNTY0Mzk4NTk3NTE5LCJldmVudElkIjo1OSwiaWRlbnRpZnlJZCI6MjEsInNlcXVlbmNlTnVtYmVyIjo4MH0=; _gat=1; d7s_spc=2; D_IID=B8A56EF1-41AA-354E-9043-89BE62F02167; D_UID=AFAAB6D4-AA5A-3B85-8E85-46E391C3C8AE; li_c=1")
    def userAgent: HttpHeader = RawHeader("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0")
    def postmanToken: RawHeader = RawHeader("Postman-Token","1f069ba0-9919-4ece-acad-4df8dcb65c21")
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "https://www.owler.com/distil_identify_cookie.html?httpReferrer=%2Fcompany%2Fknoldus&uid=3DDA8C31-0456-3C4A-8505-CD67EE2551C3"/*, headers = Seq(cookieHeader,userAgent,postmanToken)*/))




    val cook = RawHeader("Cookie","JSESSIONID=773443A914F739FD46E813F0B303EE89; vid=rBEAAl04J3mYswBJRdc5Ag==; _ga=GA1.2.536294751.1563961212; D_ZID=341F7885-7CE9-3257-B6C5-C710A1883A09; D_ZUID=73050FFD-B0E9-3C5F-9CF5-28D2DCBEFA27; D_HID=15EFE808-3D1A-3B23-9F7D-38071BAAF7CC; D_SID=103.245.119.90:XA0fKC+Ysv7sUHf6bEUXeqcr1o+N+Pv2mt2t7Kn1k0w; __qca=P0-937342400-1563961213090; d7s_uid=jyh24sd4ubd1zs; ntv_fpc=f7ce4a00-0d63-4552-bbf6-175bc12f57a8; __gads=ID=fa933cb3dd5a5404:T=1563961227:S=ALNI_MbbqHc4EDsXrIGLda1QDVnJe-tgJw; __utmz=182075681.1563966168.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); landc=errorPage; NC_VARNISH=true; fts=sNkSEdxpNsq0jvM_UIaWkA; amplitude_idowler.com=eyJkZXZpY2VJZCI6IjJkZWY2YWQ0LTIyNzEtNGE2Yi05NDVjLWY3YTY0ZWM2YzJiMlIiLCJ1c2VySWQiOiI1OTEyNTI5Iiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTYzOTY4OTkxNzA4LCJsYXN0RXZlbnRUaW1lIjoxNTYzOTY4OTkxNzM4LCJldmVudElkIjoxLCJpZGVudGlmeUlkIjowLCJzZXF1ZW5jZU51bWJlciI6MX0=; __utma=182075681.536294751.1563961212.1563966168.1563968992.2; _gid=GA1.2.1006588345.1564387682; OWLER_WEBID=5d3eabab6e0f26497d71177d; cpviewed=9231654%2C4396732%2C4396732%2C4396732; OWLER_PC=I5g35kM_neSz5l4X_4ofYXv92j0Nx7HiX1bn7eXcpjtBwEvH4dmGDLLrcN5JFuH1-I6SSlWXLHmYJWRU-QibxIJ1-MGM702IHhslq0kNhfitUeLrow_ZrSAAS4s6lwuzjvsx7xSn0dqxR8MLIUzcgw; __rtgt_sid=jyoa55ohy6ca3d; amplitude_id_e102edba5e9caea6b89e3c04fac87a4dowler.com=eyJkZXZpY2VJZCI6IjcxOGE2NTllLWU5ZTktNDUwMC1iOTgzLWJkNjI1OWI0ZjlhNFIiLCJ1c2VySWQiOiI1OTEyNTI5Iiwib3B0T3V0IjpmYWxzZSwic2Vzc2lvbklkIjoxNTY0Mzk3OTQyMTc4LCJsYXN0RXZlbnRUaW1lIjoxNTY0Mzk4NTk3NTE5LCJldmVudElkIjo1OSwiaWRlbnRpZnlJZCI6MjEsInNlcXVlbmNlTnVtYmVyIjo4MH0=; _gat=1; d7s_spc=2; D_IID=B8A56EF1-41AA-354E-9043-89BE62F02167; D_UID=AFAAB6D4-AA5A-3B85-8E85-46E391C3C8AE; li_c=1")
    val agent = RawHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36")
    val token = RawHeader("Postman-Token", "30a844fe-3b44-418b-b833-bdc17c3a87f5")
    val auth = RawHeader("Authorization", "Basic Og==")
    val cac = RawHeader("cache-control", "no-cache")
    val res =Http().singleRequest(HttpRequest(uri = "https://www.owler.com/distil_identify_cookie.html?httpReferrer=%2Fcompany%2Fpuresoftware").withHeaders(Seq(cook,agent,token,auth,cac)))
      res
      .onComplete {
        case Success(res) => {
          val HttpResponse(statusCodes, headers, entity, _) = res
          println(statusCodes)
          println(entity)
          entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach(body => println(body.utf8String))
          system.terminate()
        }
        case Failure(_) => sys.error("something wrong")
      }
  }
}