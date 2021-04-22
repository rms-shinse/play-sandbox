package com.github.sizer.sandbox.play.api.requests;

import play.libs.Json;
import play.mvc.Http;

public class Requests {
  public static <T> T fromJson(Http.Request req, Class<T> clazz) {
    return Json.fromJson(req.body().asJson(), clazz);
  }
}
