package jp.co.recruitms.auth.controllers;

import jp.co.recruitms.auth.security.Authentication;
import jp.co.recruitms.auth.security.PlayAuthenticator;
import jp.co.recruitms.database.models.User;
import jp.co.recruitms.database.db.Users;
import play.mvc.*;

public class AuthenticateController extends Controller {
  public Result signIn(String userName, String pass) {
    if (userExists(userName, pass)) {
     return ok(userName);
    } else {
      return unauthorized("User not found.");
    }
  }

  public Result signUp(String userName, String pass) {
    if (!userExists(userName, pass)){
      return Users
        .save(new User(userName, pass))
        .map(user -> ok(user.name))
        .orElseGet(() -> internalServerError("Create User failed"));
    } else {
     return badRequest("User already exists.");
    }
  }

  @Security.Authenticated(PlayAuthenticator.class)
  public Result logout(Http.Request req){
    return Authentication.getUsername(req)
      .map(Results::ok)
      .orElseGet(() ->badRequest("Not logged in."));
  }

  private boolean userExists(String userName, String password){
    return Users.findByName(userName).map(user -> user.password.equals(password)).orElse(false);
  }
}