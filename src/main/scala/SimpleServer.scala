import com.twitter.finagle.{Httpx, Service, httpx}
import com.twitter.util.{Await, Future}

object SimpleServer extends App {

  val service = new Service[httpx.Request, httpx.Response] {
    def apply(req: httpx.Request): Future[httpx.Response] =
      Future.value(
        httpx.Response(req.version, httpx.Status.Ok)
      )
  }

  val server = Httpx.serve(":8080", service)
  Await.ready(server)
}