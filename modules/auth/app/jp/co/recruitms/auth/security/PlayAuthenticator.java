package jp.co.recruitms.auth.security;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Optional;

public class PlayAuthenticator extends Security.Authenticator {
  @Override
  public Optional<String> getUsername(Http.Request req) {
    return Authentication.getUsername(req);
  }

  @Override
  public Result onUnauthorized(Http.Request req) {
    return forbidden("Need login.");
}
  }
