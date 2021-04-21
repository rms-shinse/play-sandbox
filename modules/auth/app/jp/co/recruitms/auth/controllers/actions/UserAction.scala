package jp.co.recruitms.auth.controllers.actions

import javax.inject.Inject
import jp.co.recruitms.auth.models.Users
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UserRequest[A](val userName: Option[String], request: Request[A]) extends WrappedRequest[A](request)

class UserAction @Inject() (val parser: BodyParsers.Default)(implicit val executionContext: ExecutionContext)
    extends ActionBuilder[UserRequest, AnyContent]
    with ActionTransformer[Request, UserRequest] {

  def transform[A](request: Request[A]): Future[UserRequest[A]] = Future.successful {
    request.headers
      .get("X-TOKEN")
      .map(token => Users.findByName(token).map(_.name))
      .map(userName => new UserRequest(userName, request))
      .getOrElse(new UserRequest[A](None, request))
  }
}
