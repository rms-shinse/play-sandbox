package jp.co.recruitms.health.controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HealthsController extends Controller {
  public Result index() {
    return ok("ok");
  }
}