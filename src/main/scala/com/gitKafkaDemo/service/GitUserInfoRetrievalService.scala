package com.gitKafkaDemo.service

import com.gitKafkaDemo.entities.GitUserRecord

import scala.concurrent.{ExecutionContext, Future}

class GitUserInfoRetrievalService(implicit val executionContext: ExecutionContext)  {

  var gitUserRecords=Vector.empty[GitUserRecord]

  def getGitUserRecord(userId:String):Future[Option[GitUserRecord]]=Future{

    gitUserRecords.find(_.userId==userId)
  }

}



