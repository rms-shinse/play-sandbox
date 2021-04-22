package jp.co.recruitms.auth.security;

import jp.co.recruitms.database.db.Users;
import play.mvc.Http;

import java.util.Optional;

public class Authentication {
  public static Optional<String> getUsername(Http.Request req) {
    return req.header("X-TOKEN").flatMap(token -> Users.findByName(token).map(user -> user.name));
  }

}
