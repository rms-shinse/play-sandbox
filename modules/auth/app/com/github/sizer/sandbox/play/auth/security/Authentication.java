package com.github.sizer.sandbox.play.auth.security;

import com.github.sizer.sandbox.play.database.db.Users;
import play.mvc.Http;

import java.util.Optional;

public class Authentication {
  public static Optional<String> getUsername(Http.Request req) {
    return req.header("X-TOKEN").flatMap(token -> Users.findByName(token).map(user -> user.name));
  }

}
