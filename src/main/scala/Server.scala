import com.twitter.finagle.{Httpx, Service}
import com.twitter.finagle.httpx
import com.twitter.util.{Await, Future}

object Server extends App {

  val service = new Service[httpx.Request, httpx.Response] {
    def apply(req: httpx.Request): Future[httpx.Response] = {
      println(req.path)
      Responses
      val r = req.path match {
        case "/pokus" => httpx.Response(req.version, httpx.Status.Continue)
        case _ => httpx.Response(req.version, httpx.Status.Ok)
      }
      Future.value(r)
    }
  }

  val server = Httpx.serve(":8080", service)
  Await.ready(server)
}