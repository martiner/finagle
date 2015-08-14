import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finagle.httpx.Response
import com.twitter.util.Await
import io.finch.response.Ok
import io.finch.route._
import com.twitter.finagle.Httpx

object FinchServer extends App {

  implicit val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  case class Foo(id: Int, s: String)

  val hello: Router[String] = get("hello") { "Hello, World!" }
  val hi: Router[String] = get("hi") { "Hi, World!" }
  val foo: Router[Response[Foo]] = get("foo") { Ok(Foo(1, "foo")) }

  val server = Httpx.serve(":8080", (hello :+: foo).toService)
  Await.ready(server)
}