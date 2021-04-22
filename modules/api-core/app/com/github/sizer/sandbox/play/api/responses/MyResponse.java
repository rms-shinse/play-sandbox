package com.github.sizer.sandbox.play.api.responses;

import lombok.Value;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.Results;

@Value(staticConstructor = "of")
public class MyResponse<T> {
  T payload;

  public static class Factory {
    public static <T> Result create(T payload) {
      return Results.ok(Json.toJson(MyResponse.of(payload)));
    }
  }
}
