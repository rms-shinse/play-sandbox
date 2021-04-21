package jp.co.recruitms.auth.controllers

import javax.inject.Inject
import javax.inject.Singleton
import jp.co.recruitms.auth.controllers.actions.UserAction
import jp.co.recruitms.auth.controllers.actions.UserRequest
import jp.co.recruitms.auth.models.User
import jp.co.recruitms.auth.models.Users
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents
import play.api.mvc.Request

@Singleton
class AuthenticateController @Inject() (val controllerComponents: ControllerComponents, userAction: UserAction)
    extends BaseController {
  def signIn(userName: String, pass: String): Action[AnyContent] = Action { implicit req: Request[AnyContent] =>
    if (userExists(userName, pass))
      Ok(userName)
    else
      Unauthorized("User not found.")
  }

  def signUp(userName: String, pass: String): Action[AnyContent] = Action { implicit req: Request[AnyContent] =>
    if (!userExists(userName, pass))
      Users
        .save(User(userName, pass))
        .map(user => Ok(user.name))
        .getOrElse(InternalServerError("Create User failed"))
    else
      BadRequest("User already exists.")
  }

  def logout(): Action[AnyContent] = userAction { implicit req: UserRequest[AnyContent] =>
    req.userName
      .map(name => {
        Ok(name)
      })
      .getOrElse(BadRequest("Not logged in."))
  }

  private def userExists(userName: String, password: String): Boolean =
    Users.findByName(userName).exists(_.password == password)
}
