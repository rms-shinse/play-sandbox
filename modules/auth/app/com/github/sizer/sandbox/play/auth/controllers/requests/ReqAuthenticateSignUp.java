package com.github.sizer.sandbox.play.auth.controllers.requests;


import lombok.Value;

@Value(staticConstructor = "of")
public class ReqAuthenticateSignUp {
  String userName;
  String pass;
}
