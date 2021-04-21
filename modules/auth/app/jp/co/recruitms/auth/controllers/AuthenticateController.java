package jp.co.recruitms.auth.controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class AuthenticateController extends Controller {
  public Result index() {
    return ok("ok");
  }
}