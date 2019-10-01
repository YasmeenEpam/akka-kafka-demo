package com.gitKafkaDemo

import com.sksamuel.avro4s.AvroSchema
import org.apache.avro.generic.{GenericData, GenericRecord}


case class Repo(id:Int,name:String,full_name:String, is_private: Boolean,language:String)
case class User(id:Int,login:String,location:String,email:String,bio:String,hireable:String,blog:String,company:String,repos:Seq[Repo],created_at:String,updated_at:String)


object AvroConverter extends App{

 val userSchema = AvroSchema[User]
 println(userSchema)

 val userRecord: GenericRecord = new GenericData.Record(userSchema)
 userRecord.put("id", 1)
 userRecord.put("login", "Gary")
 userRecord.put("location", "Hyderabad")
 userRecord.put("email", "test@test.com")
 userRecord.put("bio", "bio")
 userRecord.put("hireable", "Y")
 userRecord.put("blog", "http:linkedin.com")
 userRecord.put("company", "EPAM")
 userRecord.put("repos", List(Repo(1, "Gary", "Gary Epam", false, "Scala"), Repo(1, "Gary", "Gary Epam", false, "Java")))
 userRecord.put("created_at", "2019-05-24")
 userRecord.put("updated_at", "2019-05-24")
 println(userRecord)



}
