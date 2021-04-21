package jp.co.recruitms.auth.models

import scala.collection.mutable

case class User(name: String, password: String)

object Users {
  private val db: mutable.Map[String, User] = mutable.Map()

  def findByName(anUserName: String): Option[User] = db.get(anUserName)

  def save(anUser: User): Option[User] = {
    if (db.contains(anUser.name)) {
      Option.empty
    } else {
      db.put(anUser.name, anUser)
      Some(anUser)
    }
  }
}
