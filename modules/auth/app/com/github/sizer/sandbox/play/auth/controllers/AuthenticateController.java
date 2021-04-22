package com.github.sizer.sandbox.play.auth.controllers;

import com.github.sizer.sandbox.play.api.requests.Requests;
import com.github.sizer.sandbox.play.api.responses.MyResponse;
import com.github.sizer.sandbox.play.auth.controllers.requests.ReqAuthenticateSignUp;
import com.github.sizer.sandbox.play.auth.security.Authentication;
import com.github.sizer.sandbox.play.auth.security.PlayAuthenticator;
import com.github.sizer.sandbox.play.database.models.User;
import com.github.sizer.sandbox.play.database.db.Users;
import lombok.Value;
import play.mvc.*;

public class AuthenticateController extends Controller {

  @Value(staticConstructor = "of")
  public static class ResSignIn {
    String userName;
  }

  public Result signIn(String userName, String pass) {
    if (userExists(userName, pass)) {
      return MyResponse.Factory.create(ResSignIn.of(userName));
    } else {
      return unauthorized("User not found.");
    }
  }

  @Value(staticConstructor = "of")
  public static class ResSignUp {
    String userName;
  }

  public Result signUp(Http.Request req) {
    final ReqAuthenticateSignUp body = Requests.fromJson(req, ReqAuthenticateSignUp.class);
    if (!userExists(body.getUserName(), body.getPass())){
      return Users
        .save(new User(body.getUserName(), body.getPass()))
        .map(user -> MyResponse.Factory.create(ResSignUp.of(body.getUserName())))
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
