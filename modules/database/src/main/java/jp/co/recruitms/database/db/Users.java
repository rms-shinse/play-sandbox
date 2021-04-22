package jp.co.recruitms.database.db;

import jp.co.recruitms.database.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Users {
  private static final Map<String, User> db = new HashMap<>();

  static public Optional<User> findByName(String anUserName) {
    return Optional.ofNullable(db.getOrDefault(anUserName, null));
  }

  static public Optional<User> save(User anUser){
    if (db.containsKey(anUser.name)) {
      return Optional.empty();
    } else {
      db.put(anUser.name, anUser);
      return Optional.of(anUser);
    }
  }
}
