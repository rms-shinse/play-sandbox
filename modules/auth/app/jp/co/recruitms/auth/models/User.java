package jp.co.recruitms.auth.models;


public class User {
  public String name;
  public String password;

  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }
}
